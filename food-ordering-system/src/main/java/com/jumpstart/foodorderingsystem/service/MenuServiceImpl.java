package com.jumpstart.foodorderingsystem.service;

import com.jumpstart.foodorderingsystem.dto.MenuDto;
import com.jumpstart.foodorderingsystem.entity.Category;
import com.jumpstart.foodorderingsystem.entity.Menu;
import com.jumpstart.foodorderingsystem.exception.CategoryNotFoundException;
import com.jumpstart.foodorderingsystem.repository.CategoryRepository;
import com.jumpstart.foodorderingsystem.repository.MenuRepository;
import com.jumpstart.foodorderingsystem.response.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    public MenuServiceImpl(MenuRepository menuRepository,
                           CategoryRepository categoryRepository) {

        this.menuRepository = menuRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Response<MenuDto> createMenu(MenuDto dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException("Category not found"));

        Menu menu = new Menu();

        menu.setName(dto.getName());
        menu.setDescription(dto.getDescription());
        menu.setPrice(dto.getPrice());
        menu.setImageUrl(dto.getImageUrl());
        menu.setCategory(category);

        Menu saved = menuRepository.save(menu);

        dto.setId(saved.getId());
        dto.setCategoryName(category.getName());

        return Response.success("Menu created successfully", dto);
    }

    @Override
    public Response<List<MenuDto>> getAllMenus() {

        List<MenuDto> menus = menuRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return Response.success("Menus retrieved successfully", menus);
    }

    @Override
    public Response<MenuDto> getMenuById(Long id) {

        Menu menu = menuRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Menu not found"));

        return Response.success("Menu retrieved successfully",
                convertToDto(menu));
    }

    private MenuDto convertToDto(Menu menu) {

        return MenuDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .imageUrl(menu.getImageUrl())
                .categoryId(menu.getCategory().getId())
                .categoryName(menu.getCategory().getName())
                .build();
    }
}