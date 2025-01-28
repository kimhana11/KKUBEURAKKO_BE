package com.example.kkubeurakko.api.controller.menu.request;

import jakarta.validation.constraints.NotNull;

public record MenuImgRequest(
	@NotNull
	String label
) {
}
