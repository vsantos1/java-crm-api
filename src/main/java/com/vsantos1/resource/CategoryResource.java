package com.vsantos1.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vsantos1.model.Category;
import com.vsantos1.repository.CategoryRepository;

@RestController
@RequestMapping(value = "/api/v1")
public class CategoryResource {

    private final CategoryRepository categoryRepository;

    public CategoryResource(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @GetMapping(value = "/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.findAll());
    }

    @GetMapping(value = "/categories/{category_id}")
    public ResponseEntity<Category> getCategory(@PathVariable("category_id") Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if(optionalCategory.isEmpty()){
            throw new RuntimeException("No records found for this ID");
        }

        return ResponseEntity.status(HttpStatus.OK).body(optionalCategory.get());
    }

    @PostMapping(value = "/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,HttpServletResponse response){
        Category entity = categoryRepository.save(category);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{category_id}")
        .buildAndExpand(entity.getId()).toUri();

        response.setHeader("Location",uri.toASCIIString());

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }


}
