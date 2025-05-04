package com.saandeep.govrn.controller;

import com.saandeep.govrn.dto.EntityDTO;
import com.saandeep.govrn.dto.PersonDTO;
import com.saandeep.govrn.model.Person;
import com.saandeep.govrn.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping("/")
    public ResponseEntity<List<Person>> getUsers() {
        List<Person> persons = personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Person> createUser(@Valid @RequestBody PersonDTO personDto) {
        Person createdPerson = personService.createPerson(personDto);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updateUser(@RequestBody Person person, @PathVariable Long id) {
        Person updatedPerson = personService.updatePerson(id, person);
        return new ResponseEntity<>(updatedPerson, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deleteUser(@PathVariable Long id) {
        Person person = personService.deletePerson(id, true);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/managers/add/{aadhaarNumber}")
    public ResponseEntity<EntityDTO<Person>> addManager(@PathVariable String aadhaarNumber) {
        EntityDTO<Person> entityDTO = personService.addManager(aadhaarNumber);
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }
}
