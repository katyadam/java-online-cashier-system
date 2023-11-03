package com.example.onlinecashiersystem.service;

import com.example.onlinecashiersystem.api.CategoryDto;
import com.example.onlinecashiersystem.data.model.Category;
import com.example.onlinecashiersystem.data.repository.CategoryRepository;
import com.example.onlinecashiersystem.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;


    @Autowired
    public CategoryService(CategoryRepository categoryRepository, UserService userService) {
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id: " + id + " was not found!"));
    }

    @Transactional
    public Category createCategory(CategoryDto categoryDto) {
        Category newCategory = new Category();
        newCategory.setName(categoryDto.name());
        newCategory.setUser(userService.findById(categoryDto.userId()));

        return categoryRepository.save(newCategory);
    }

    @Transactional
    public Category updateCategory(Long id, CategoryDto categoryDto) {
        Category toUpdate = findById(id);
        toUpdate.setName(categoryDto.name());
        toUpdate.setUser(userService.findById(categoryDto.userId()));

        categoryRepository.save(toUpdate);
        return toUpdate;
    }
}
