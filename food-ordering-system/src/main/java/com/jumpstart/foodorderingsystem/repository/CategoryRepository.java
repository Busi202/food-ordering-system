package com.jumpstart.foodorderingsystem.repository;

import com.jumpstart.foodorderingsystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository responsible for database operations
 * related to Category entities.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}