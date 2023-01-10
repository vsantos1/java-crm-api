package com.vsantos1.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.vsantos1.exception.ResourceNotFoundException;
import com.vsantos1.model.Person;
import com.vsantos1.repository.PersonRepository;

@Service
public class PersonService {
    

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    

    public Person update(Long id,Person person){
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            throw new ResourceNotFoundException("No records found for this ID");
        }
        BeanUtils.copyProperties(person, optionalPerson.get(), "id");
    
        return personRepository.save(optionalPerson.get());
    }
}
