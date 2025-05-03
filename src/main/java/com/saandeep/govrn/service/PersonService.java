package com.saandeep.govrn.service;

import com.saandeep.govrn.dto.PersonDTO;
import com.saandeep.govrn.model.Person;
import com.saandeep.govrn.model.Region;
import com.saandeep.govrn.repository.PersonRepository;
import com.saandeep.govrn.util.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    RegionService regionService;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
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
}
