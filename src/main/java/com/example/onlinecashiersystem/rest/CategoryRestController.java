package com.example.onlinecashiersystem.rest;

import com.example.onlinecashiersystem.api.CategoryDto;
import com.example.onlinecashiersystem.data.model.Category;
import com.example.onlinecashiersystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDto));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Category> update(
            @PathVariable("id") Long id,
            @RequestBody CategoryDto categoryDto
    ) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }
}
