package com.example.kkubeurakko.api.controller.menu.request;

import jakarta.validation.constraints.NotNull;

public record MenuImgRequest(
	@NotNull(message = "잘못된 카테고리 요청입니다.")
	String category
) {
}
