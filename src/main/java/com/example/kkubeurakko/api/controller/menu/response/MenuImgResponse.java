package com.example.kkubeurakko.api.controller.menu.response;

import java.math.BigDecimal;

public record MenuImgResponse(
	String title,	// 이미지 제목
	byte[] img,		// 이미지
	BigDecimal price	//메뉴 가격
) {
}
