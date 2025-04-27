package com.saandeep.govrn.controller;

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
}
