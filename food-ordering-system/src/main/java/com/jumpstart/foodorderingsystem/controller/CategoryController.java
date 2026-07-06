package com.jumpstart.foodorderingsystem.controller;

import com.jumpstart.foodorderingsystem.dto.CategoryDto;
import com.jumpstart.foodorderingsystem.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Handles incoming HTTP requests
 * related to categories.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @GetMapping("/{id}")
    public CategoryDto getCategoryById(
            @PathVariable Long id) {

        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(
            @Valid @RequestBody CategoryDto categoryDto){

        CategoryDto createdCategory =
                categoryService.addCategory(categoryDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryDto categoryDto) {

        CategoryDto updatedCategory =
                categoryService.updateCategory(id, categoryDto);

        return ResponseEntity.ok(updatedCategory);
    }
}