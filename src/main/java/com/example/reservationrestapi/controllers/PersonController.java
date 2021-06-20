package com.example.reservationrestapi.controllers;

import com.example.reservationrestapi.exceptions.person.PersonNotFoundException;
import com.example.reservationrestapi.model.Person;
import com.example.reservationrestapi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    //ROLE_ADMIN only access
    @GetMapping("/restricted/person")
    public List<Person> GetAllPeople() {
        return (List<Person>) personRepository.findAll();
    }

    //general login access
    @GetMapping({"/protected/person/{id}"})
    public Person getOnePerson(@PathVariable Integer id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping({"/data/person/{id}", "/data/person"})
    public Person replacePerson(@RequestBody Person newPerson,
                                @PathVariable(required = false) Integer id) {
        if (id == null) {
            return personRepository.save(newPerson);
        }
        return personRepository.findById(id).map(person -> {
            person.setFirstName(newPerson.getFirstName());
            person.setLastName(newPerson.getLastName());
            return personRepository.save(person);
        }).orElseGet(() -> {
            newPerson.setId(id);
            return personRepository.save(newPerson);
        });
    }

    //general login access
    @DeleteMapping("/protected/person/{id}")
    public void deletePerson(@PathVariable Integer id) {
        try {
            personRepository.deleteById(id);
        } catch (Exception e) {
            throw new PersonNotFoundException(id);
        }
    }
}
