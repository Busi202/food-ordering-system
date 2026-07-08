package com.jumpstart.foodorderingsystem.service;

import com.jumpstart.foodorderingsystem.entity.Category;
import com.jumpstart.foodorderingsystem.entity.Menu;
import com.jumpstart.foodorderingsystem.exception.CategoryNotFoundException;
import com.jumpstart.foodorderingsystem.repository.CategoryRepository;
import com.jumpstart.foodorderingsystem.repository.MenuRepository;
import com.jumpstart.foodorderingsystem.dto.MenuDto;
import com.jumpstart.foodorderingsystem.response.Response;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    public MenuServiceImpl(MenuRepository menuRepository,
                           CategoryRepository categoryRepository) {

        this.menuRepository = menuRepository;
        this.categoryRepository = categoryRepository;

    }
    private Menu toEntity(MenuDto dto, Category category) {

        Menu menu = new Menu();

        menu.setName(dto.getName());
        menu.setDescription(dto.getDescription());
        menu.setPrice(dto.getPrice());
        menu.setImageUrl(dto.getImageUrl());
        menu.setCategory(category);

        return menu;

    }
    private MenuDto toDto(Menu menu) {

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

    @Override
    public Response<MenuDto> createMenu(MenuDto dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())

                .orElseThrow(() ->
                        new CategoryNotFoundException("Category not found"));

        Menu menu = toEntity(dto, category);

        Menu savedMenu = menuRepository.save(menu);

        MenuDto responseDto = toDto(savedMenu);

        return Response.success("Menu created successfully", responseDto);

    }
    @Override
    public Response<List<MenuDto>> getAllMenus() {

        List<MenuDto> menus = menuRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();

        return Response.success(
                "Menus retrieved successfully",
                menus
        );

    }
    @Override
    public Response<MenuDto> getMenuById(Long id) {

        Menu menu = menuRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Menu not found"));

        return Response.success(
                "Menu retrieved successfully",
                toDto(menu)
        );

    }
    @Override
    public Response<MenuDto> updateMenu(Long id, MenuDto dto) {

        Menu menu = menuRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Menu not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException("Category not found"));

        menu.setName(dto.getName());
        menu.setDescription(dto.getDescription());
        menu.setPrice(dto.getPrice());
        menu.setImageUrl(dto.getImageUrl());
        menu.setCategory(category);

        Menu updated = menuRepository.save(menu);

        return Response.success(
                "Menu updated successfully",
                toDto(updated)
        );

    }
    @Override
    public Response<String> deleteMenu(Long id) {

        Menu menu = menuRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Menu not found"));

        menuRepository.delete(menu);

        return Response.success(
                "Menu deleted successfully",
                "Deleted"
        );

    }

}