package com.saandeep.govrn.service;

import com.saandeep.govrn.dto.PersonDTO;
import com.saandeep.govrn.dto.EntityDTO;
import com.saandeep.govrn.model.Person;
import com.saandeep.govrn.model.Project;
import com.saandeep.govrn.model.Region;
import com.saandeep.govrn.repository.PersonRepository;
import com.saandeep.govrn.util.constants.Texts;
import com.saandeep.govrn.util.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PersonService extends BaseService<Person> {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    RegionService regionService;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPerson(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person createPerson(PersonDTO personDto) {
        Region region = regionService.getRegion(personDto.getRegionId());
        UserRole role = personDto.getRole();
        Person person = new Person(null,
                personDto.getAadhaarNumber(),
                personDto.getEmail(),
                personDto.getPassword(),
                role != null ? role : UserRole.CITIZEN,
                true,
                region,
                null
                );
        return personRepository.save(person);
    }

    public Person updatePerson(Long personId, Person person) {
        Person fetchedPerson = getPerson(personId);
        if(fetchedPerson == null) {
            return null;
        }

        person.setId(personId);
        return personRepository.save(person);
    }

    public Person deletePerson(Long personId, boolean isSoft) {
        Person fetchedPerson = getPerson(personId);
        if(fetchedPerson == null) return null;

        if(!isSoft) {
            personRepository.deleteById(personId);
            return fetchedPerson;
        }

        fetchedPerson.setDeletedAt(LocalDateTime.now());
        return personRepository.save(fetchedPerson);
    }

    public void addProjectToManager(Person manager, Project newProject) {
        Set<Project> projects = manager.getProjects();
        if(projects == null) {
            projects = new HashSet<>();
        }
        projects.add(newProject);
        manager.setProjects(projects);
        personRepository.save(manager);
    }

    public EntityDTO<Person> addManager(String aadhaarNumber) {
        Person person = personRepository.findByAadhaarNumber(aadhaarNumber);
        if(person == null) {
            return getResponseDTO(List.of(getEntityNotFound(Person.class)), Texts.USER_NOT_FOUND, null);
        }

        person.setRole(UserRole.MANAGER);
        person =  personRepository.save(person);
        return getResponseDTO(null, "", person);
    }
}
