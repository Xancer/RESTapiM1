package com.dorokhov.RESTapi.controllers;

import com.dorokhov.RESTapi.dao.PersonRepository;
import com.dorokhov.RESTapi.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;
    @GetMapping
    public ResponseEntity<List<Person>> listAllPersons() {
       /* personRepository.save(new Person("Slava","Dorokhov"));
        personRepository.save(new Person("Igor","sherin"));
        personRepository.save(new Person("Sergey","Fityak"));
        */
        List<Person> persons = personRepository.findAll();
        return ResponseEntity.ok().body(persons);
    }

    @GetMapping(value = "/{personId}")
    public ResponseEntity<Person> getPerson(@PathVariable("personId") Long personId)
            throws EntityNotFoundException {
        Optional<Person> person = personRepository.findById(personId);
        if (!person.isPresent())
            throw new EntityNotFoundException("id-" + personId);
        return ResponseEntity.ok().body(person.get());
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody @Valid Person person) {
        Person p = personRepository.save(person);
        return ResponseEntity.status(201).body(p);
    }

    @PutMapping(value = "/{personId}")
    public ResponseEntity<Person> updatePerson(@RequestBody @Valid Person person,@PathVariable("personId") Long personId) throws EntityNotFoundException {
        Optional<Person> p = personRepository.findById(personId);
        if (!p.isPresent())
            throw new EntityNotFoundException("id-" + personId);
        return ResponseEntity.ok().body(personRepository.save(person));
    }

    @DeleteMapping(value = "/{personId}")
    public ResponseEntity<Person> deletePerson(@PathVariable("personId") Long personId)
            throws EntityNotFoundException {
        Optional<Person> p = personRepository.findById(personId);
        if (!p.isPresent())
            throw new EntityNotFoundException("id-" + personId);
        personRepository.deleteById(personId);
        return ResponseEntity.ok().body(p.get());
    }
}