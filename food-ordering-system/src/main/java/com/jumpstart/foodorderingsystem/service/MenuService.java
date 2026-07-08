package com.jumpstart.foodorderingsystem.service;

import com.jumpstart.foodorderingsystem.dto.MenuDto;
import com.jumpstart.foodorderingsystem.response.Response;

public interface MenuService {

    Response<MenuDto> createMenu(MenuDto dto);

}
