package com.jumpstart.foodorderingsystem.controller;

import com.jumpstart.foodorderingsystem.dto.MenuDto;
import com.jumpstart.foodorderingsystem.response.Response;
import com.jumpstart.foodorderingsystem.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity<Response<MenuDto>> createMenu(
            @Valid @RequestBody MenuDto dto) {

        return ResponseEntity.ok(menuService.createMenu(dto));
    }

    @GetMapping
    public ResponseEntity<Response<Page<MenuDto>>> getAllMenus(

            @RequestParam(required = false)
            Long categoryId,

            @RequestParam(required = false)
            String search,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            @RequestParam(defaultValue = "id,asc")
            String sort) {

        return ResponseEntity.ok(
                menuService.getAllMenus(
                        categoryId,
                        search,
                        page,
                        size,
                        sort
                )
        );

    }
    @GetMapping("/{id}")
    public ResponseEntity<Response<MenuDto>> getMenuById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                menuService.getMenuById(id)
        );

    }
    @PutMapping("/{id}")
    public ResponseEntity<Response<MenuDto>> updateMenu(

            @PathVariable Long id,

            @Valid @RequestBody MenuDto dto) {

        return ResponseEntity.ok(

                menuService.updateMenu(id, dto)

        );

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> deleteMenu(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                menuService.deleteMenu(id)
        );

    }
}