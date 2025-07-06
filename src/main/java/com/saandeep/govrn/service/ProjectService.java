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

    public EntityDTO<Project> transitionToNextStage(Long projectId) {
        List<String> errors = new ArrayList<>();

        Project project = projectRepository.findById(projectId).orElse(null);
        if(project == null) {
            errors.add(getEntityNotFound(Project.class));
            return getResponseDTO(errors, Texts.PROJECT_NOT_FOUND, null);
        }

        ProjectStatusMetaData psmd = statusMetaDataService.getLatestStageData(projectId);
        ProjectStatus status = psmd.getStatus();

        if(status == ProjectStatus.ABANDONED || status == ProjectStatus.PROJECT_COMPLETED) {
            errors.add(getEntityNotFound(ProjectStatus.class));
            return getResponseDTO(errors, Texts.TRANSITION_NOT_POSSIBLE, project);
        }

        status = ProjectStatus.getStageFromInteger(status.ordinal()+1);
        project = statusMetaDataService.createAndAddStatusMetaData(project, status);
        project = projectRepository.save(project);

        return getResponseDTO(null, "", project);
    }

    public EntityDTO<List<Project>> startVoting(LocalDateTime startDate, List<Long> projectIdList) {
        List<String> errors = new ArrayList<>();

        List<Project> projects = projectRepository.findAllById(projectIdList);
        List<Long> newProjectIdList = new ArrayList<>();

        for(int i=0;i<projects.size();i++) {
            Project project = projects.get(i);
            ProjectStatusMetaData statusMetaData = statusMetaDataService.getLatestStageData(project.getId());

            if(statusMetaData.getStatus() != ProjectStatus.VOTING_NOT_STARTED) {
                errors.add(project.getId().toString());
                continue;
            }

            newProjectIdList.add(project.getId());
            Project updatedProject = statusMetaDataService.createAndAddStatusMetaData(project, ProjectStatus.VOTING_IN_PROGRESS);
            updatedProject = projectRepository.save(updatedProject);
            projects.set(i, updatedProject);
        }

        projectRepository.updateStartDate(startDate, projectIdList);
        return new EntityDTO<>(errors, Texts.TRANSITION_NOT_POSSIBLE + " to " + Texts.VOTING_IN_PROGRESS, projects);
    }
}
