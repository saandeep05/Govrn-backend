package com.saandeep.govrn.service;

import com.saandeep.govrn.dto.EntityDTO;
import com.saandeep.govrn.dto.ProjectDTO;
import com.saandeep.govrn.model.Person;
import com.saandeep.govrn.model.Project;
import com.saandeep.govrn.model.ProjectStatusMetaData;
import com.saandeep.govrn.model.Region;
import com.saandeep.govrn.repository.ProjectRepository;
import com.saandeep.govrn.util.constants.Texts;
import com.saandeep.govrn.util.enums.ProjectStatus;
import com.saandeep.govrn.util.enums.UserRole;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Service
public class ProjectService extends BaseService<Project> {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private ProjectStatusMetaDataService statusMetaDataService;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public Project createProject(@Valid ProjectDTO projectDTO, Long personId) {
        Person manager = personService.getPerson(personId);
        if(manager == null) return null;

        Set<Person> managers = new HashSet<>();
        managers.add(manager);
        Region region = manager.getRegion();

        Project project = new Project(
                null,
                projectDTO.getName(),
                projectDTO.getDescription(),
                projectDTO.getProposedDate(),
                null,
                null,
                null,
                managers,
                region
        );

        Project newProject = projectRepository.save(project);

        newProject = statusMetaDataService.createAndAddStatusMetaData(project, ProjectStatus.VOTING_NOT_STARTED);
        personService.addProjectToManager(manager, newProject);
        regionService.addProjectToRegion(region, newProject);

        return projectRepository.save(newProject);
    }

    public EntityDTO<Project> assignProjectTo(Long projectId, Long personId) {
        List<String> errors = new ArrayList<>();
        Project project = getProject(projectId);
        if(project == null) {
            errors.add(getEntityNotFound(Project.class));
            return getResponseDTO(errors, Texts.PROJECT_NOT_FOUND, null);
        }

        Person person = personService.getPerson(personId);
        if(person == null) {
            errors.add(getEntityNotFound(Person.class));
            return getResponseDTO(errors, Texts.USER_NOT_FOUND, project);
        }

        Set<Person> managers = project.getManagers();
        if(managers == null) {
            managers = new HashSet<>();
        }
        managers.add(person);
        project.setManagers(managers);
        project = projectRepository.save(project);


        Set<Project> projects = person.getProjects();
        if(projects == null) {
            projects = new HashSet<>();
        }
        projects.add(project);
        person.setProjects(projects);
        UserRole role = person.getRole();
        if(role == UserRole.CITIZEN) {
            person.setRole(UserRole.MANAGER);
        }
        personService.updatePerson(person.getId(), person);

        return getResponseDTO(errors, "", project);
    }
}
