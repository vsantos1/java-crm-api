package com.vsantos1.resource;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsantos1.model.Person;
import com.vsantos1.repository.PersonRepository;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/api/v1")
public class PersonResource {
    
    private final PersonRepository personRepository;

    public PersonResource(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @GetMapping(value = "/persons")
    public ResponseEntity<List<Person>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.findAll());
    }

    @PostMapping(value="/persons", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> createPerson (@RequestBody Person person) {
        
        Person entity = personRepository.save(person);


        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }
    

}
