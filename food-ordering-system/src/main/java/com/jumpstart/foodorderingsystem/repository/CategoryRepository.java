package com.jumpstart.foodorderingsystem.repository;

import com.jumpstart.foodorderingsystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}