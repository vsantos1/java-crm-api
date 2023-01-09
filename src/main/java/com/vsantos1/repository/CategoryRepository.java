package com.vsantos1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vsantos1.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    
}
