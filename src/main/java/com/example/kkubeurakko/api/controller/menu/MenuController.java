package com.example.kkubeurakko.api.controller.menu;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.kkubeurakko.api.service.menu.MenuService;
import com.example.kkubeurakko.global.common.CommonResponse;
import com.example.kkubeurakko.global.common.ResponseMsgEnum;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MenuController {
	private final MenuService menuService;
	@GetMapping("/api/menu/{category}")
	public ResponseEntity<CommonResponse> findMenuImgForCategory(@PathVariable String category){
		List<byte[]> filesData = menuService.getMenuListForCategory(category);
		return ResponseEntity.status(HttpStatus.OK).body(
			new CommonResponse(
				ResponseMsgEnum.FIND_MENU_IMG_LIST_SUCCESS.getCode(),
				ResponseMsgEnum.FIND_MENU_IMG_LIST_SUCCESS.getResponseMsg(),
				filesData
			)
		);
	}
}
