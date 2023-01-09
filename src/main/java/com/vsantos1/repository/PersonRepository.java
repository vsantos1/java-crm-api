package com.vsantos1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vsantos1.model.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {
    
}
