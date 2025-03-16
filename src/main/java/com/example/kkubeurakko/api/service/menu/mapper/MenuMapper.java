package com.example.kkubeurakko.api.service.menu.mapper;

import org.mapstruct.Mapper;

import com.example.kkubeurakko.api.controller.menu.response.MenuImgResponse;
import com.example.kkubeurakko.domain.menu.Menu;

@Mapper(componentModel = "spring")
public interface MenuMapper {
	MenuImgResponse menuToMenuImgResponse(Menu menu);
}
