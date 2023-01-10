package com.vsantos1.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.vsantos1.exception.ResourceNotFoundException;
import com.vsantos1.model.Category;
import com.vsantos1.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category update(Long id, Category category){
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isEmpty()) {
            throw new ResourceNotFoundException("No records found for this ID");
        }
        BeanUtils.copyProperties(category, optionalCategory);
        
        return categoryRepository.save(optionalCategory.get());
    }
}
