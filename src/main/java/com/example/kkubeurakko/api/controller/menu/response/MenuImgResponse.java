package com.example.kkubeurakko.api.controller.menu.response;

import java.math.BigDecimal;

public record MenuImgResponse(
	Long id,		// 메뉴 id
	String name,	// 이미지 제목
	String imageUrl,		//이미지 url
	BigDecimal price	//메뉴 가격
) {
}
