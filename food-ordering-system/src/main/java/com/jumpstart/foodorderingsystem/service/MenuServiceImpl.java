package com.jumpstart.foodorderingsystem.service;

import com.jumpstart.foodorderingsystem.entity.Category;
import com.jumpstart.foodorderingsystem.entity.Menu;
import com.jumpstart.foodorderingsystem.exception.CategoryNotFoundException;
import com.jumpstart.foodorderingsystem.repository.CategoryRepository;
import com.jumpstart.foodorderingsystem.repository.MenuRepository;
import com.jumpstart.foodorderingsystem.dto.MenuDto;
import com.jumpstart.foodorderingsystem.response.Response;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.jumpstart.foodorderingsystem.exception.MenuNotFoundException;

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
    public Response<Page<MenuDto>> getAllMenus(
            Long categoryId,
            String search,
            int page,
            int size,
            String sort) {

        String[] sortParts = sort.split(",");

        Sort.Direction direction =
                sortParts[1].equalsIgnoreCase("desc")
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(direction, sortParts[0]));

        Page<Menu> menuPage;

        if (categoryId != null && search != null && !search.isBlank()) {

            menuPage = menuRepository
                    .findByCategoryIdAndNameContainingIgnoreCase(
                            categoryId,
                            search,
                            pageable);

        } else if (categoryId != null) {

            menuPage = menuRepository
                    .findByCategoryId(
                            categoryId,
                            pageable);

        } else if (search != null && !search.isBlank()) {

            menuPage = menuRepository
                    .findByNameContainingIgnoreCase(
                            search,
                            pageable);

        } else {

            menuPage = menuRepository.findAll(pageable);

        }

        Page<MenuDto> dtoPage =
                menuPage.map(this::toDto);

        return Response.success(
                "Menus retrieved successfully",
                dtoPage);

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
    public Response<MenuDto> getMenuById(Long id) {

        Menu menu = menuRepository.findById(id)
                .orElseThrow(() ->
                        new MenuNotFoundException("Menu not found"));

        return Response.success(
                "Menu retrieved successfully",
                toDto(menu)
        );

    }
    @Override
    public Response<MenuDto> updateMenu(Long id, MenuDto dto) {

        Menu menu = menuRepository.findById(id)
                .orElseThrow(() ->
                        new MenuNotFoundException("Menu not found"));

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
                        new MenuNotFoundException("Menu not found"));

        menuRepository.delete(menu);

        return Response.success(
                "Menu deleted successfully",
                "Deleted"
        );

    }

}