package com.vsantos1.resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsantos1.event.CreatedResourceEvent;
import com.vsantos1.exception.ResourceNotFoundException;
import com.vsantos1.model.Person;
import com.vsantos1.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/api/v1")
public class PersonResource {

    private final PersonRepository personRepository;

    private final ApplicationEventPublisher publisher;

    public PersonResource(PersonRepository personRepository, ApplicationEventPublisher publisher) {
        this.personRepository = personRepository;
        this.publisher = publisher;
    }

    @GetMapping(value = "/persons")
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.findAll());
    }

    @GetMapping(value = "persons/{person_id}")
    public ResponseEntity<Person> getById(@PathVariable("person_id") Long id) {

        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            throw new ResourceNotFoundException("No records found for this ID");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalPerson.get());

    }

    @PostMapping(value = "/persons", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> create(@RequestBody Person person, HttpServletResponse response) {

        Person entity = personRepository.save(person);
        publisher.publishEvent(new CreatedResourceEvent(entity, response, entity.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @PutMapping(value = "/persons/{person_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> update(@PathVariable("person_id") Long id, @RequestBody Person person) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            throw new ResourceNotFoundException("No records found for this ID");
        }
        BeanUtils.copyProperties(person, optionalPerson.get(), "id");

        return ResponseEntity.status(HttpStatus.OK).body(personRepository.save(optionalPerson.get()));

    }

    @DeleteMapping(value = "/persons/{person_id}")
    public ResponseEntity<?> deletePerson(@PathVariable("person_id") Long id) {
        personRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
