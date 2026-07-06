package com.jumpstart.foodorderingsystem.controller;

import com.jumpstart.foodorderingsystem.response.Response;
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
import org.springframework.web.bind.annotation.DeleteMapping;

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
    public ResponseEntity<Response<List<CategoryDto>>> getCategories() {

        List<CategoryDto> categories =
                categoryService.getAllCategories();

        return ResponseEntity.ok(
                Response.success(
                        "Categories retrieved successfully",
                        categories
                )
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response<CategoryDto>> getCategoryById(
            @PathVariable Long id) {

        CategoryDto dto =
                categoryService.getCategoryById(id);

        return ResponseEntity.ok(
                Response.success(
                        "Category retrieved successfully",
                        dto
                )
        );
    }

    @PostMapping
    public ResponseEntity<Response<CategoryDto>> addCategory(
            @Valid @RequestBody CategoryDto categoryDto) {

        CategoryDto createdCategory =
                categoryService.addCategory(categoryDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        Response.success(
                                "Category created successfully",
                                createdCategory
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<CategoryDto>> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryDto categoryDto) {

        CategoryDto updatedCategory =
                categoryService.updateCategory(id, categoryDto);

        return ResponseEntity.ok(
                Response.success(
                        "Category updated successfully",
                        updatedCategory
                )
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteCategory(
            @PathVariable Long id) {

        categoryService.deleteCategory(id);

        return ResponseEntity.ok(
                Response.success(
                        "Category deleted successfully",
                        null
                )
        );
    }
}