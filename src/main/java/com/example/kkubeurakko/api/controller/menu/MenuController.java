package com.example.kkubeurakko.api.controller.menu;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.kkubeurakko.api.controller.menu.request.MenuImgRequest;
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
	public ResponseEntity<CommonResponse> findMenuImgForCategory(@RequestBody MenuImgRequest menuImgRequest){

		List<MenuImgResponse> menuImgResponseList = menuService.getMenuListForCategory(menuImgRequest.label());
		return ResponseEntity.status(HttpStatus.OK).body(
			new CommonResponse(
				ResponseMsgEnum.FIND_MENU_IMG_LIST_SUCCESS.getCode(),
				ResponseMsgEnum.FIND_MENU_IMG_LIST_SUCCESS.getResponseMsg(),
				menuImgResponseList
			)
		);
	}
}
