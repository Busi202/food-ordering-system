package com.jumpstart.foodorderingsystem.service;

import com.jumpstart.foodorderingsystem.dto.CategoryDto;

import java.util.List;

/**
 * Defines business operations related to categories.
 */
public interface CategoryService {

    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Long id);
    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(Long id, CategoryDto categoryDto);
}