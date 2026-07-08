package com.jumpstart.foodorderingsystem.controller;

import com.jumpstart.foodorderingsystem.dto.MenuDto;
import com.jumpstart.foodorderingsystem.response.Response;
import com.jumpstart.foodorderingsystem.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public ResponseEntity<Response<List<MenuDto>>> getAllMenus() {

        return ResponseEntity.ok(
                menuService.getAllMenus()
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