package com.jumpstart.foodorderingsystem.controller;

import com.jumpstart.foodorderingsystem.dto.MenuDto;
import com.jumpstart.foodorderingsystem.response.Response;
import com.jumpstart.foodorderingsystem.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}