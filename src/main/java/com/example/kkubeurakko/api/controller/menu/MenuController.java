package com.example.kkubeurakko.api.controller.menu;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.kkubeurakko.api.controller.menu.response.MenuImgResponse;
import com.example.kkubeurakko.api.service.menu.MenuService;
import com.example.kkubeurakko.global.common.CommonResponse;
import com.example.kkubeurakko.global.common.ResponseMsgEnum;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MenuController {
	private final MenuService menuService;
	@PostMapping("/api/menulist")
	public ResponseEntity<CommonResponse> findMenuImg(@RequestParam String classification, @RequestParam String variable){

		List<MenuImgResponse> menuImgResponseList = menuService.getMenuList(classification ,variable);
		return ResponseEntity.status(HttpStatus.OK).body(
			new CommonResponse(
				ResponseMsgEnum.FIND_MENU_IMG_LIST_SUCCESS.getCode(),
				ResponseMsgEnum.FIND_MENU_IMG_LIST_SUCCESS.getResponseMsg(),
				menuImgResponseList
			)
		);
	}
}
