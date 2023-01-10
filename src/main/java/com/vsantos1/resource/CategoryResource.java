package com.vsantos1.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsantos1.event.CreatedResourceEvent;
import com.vsantos1.exception.ResourceNotFoundException;
import com.vsantos1.model.Category;
import com.vsantos1.repository.CategoryRepository;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value = "/api/v1")
public class CategoryResource {

    private final CategoryRepository categoryRepository;

    private final ApplicationEventPublisher publisher;

    public CategoryResource(CategoryRepository categoryRepository, ApplicationEventPublisher publisher) {
        this.categoryRepository = categoryRepository;
        this.publisher = publisher;
    }

    @GetMapping(value = "/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.findAll());
    }

    @GetMapping(value = "/categories/{category_id}")
    public ResponseEntity<Category> getCategory(@PathVariable("category_id") Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isEmpty()) {
            throw new ResourceNotFoundException("No records found for this ID");
        }

        return ResponseEntity.status(HttpStatus.OK).body(optionalCategory.get());
    }

    @PostMapping(value = "/categories")
    public ResponseEntity<Category> create(@RequestBody Category category, HttpServletResponse response) {
        Category entity = categoryRepository.save(category);

        publisher.publishEvent(new CreatedResourceEvent(entity, response, entity.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @PutMapping(value = "/categories/{category_id}")
    public ResponseEntity<Category> update(@PathVariable("category_id") Long id, @RequestBody Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isEmpty()) {
            throw new ResourceNotFoundException("No records found for this ID");
        }
        BeanUtils.copyProperties(category, optionalCategory);
        return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.save(category));
    }

    @DeleteMapping(value = "/categories/{category_id}")
    public ResponseEntity<?> deletePerson(@PathVariable("category_id") Long id) {
        categoryRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
