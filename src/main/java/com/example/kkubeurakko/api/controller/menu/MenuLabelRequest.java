package com.example.kkubeurakko.api.controller.menu;

import jakarta.validation.constraints.NotBlank;

public record MenuLabelRequest(
	@NotBlank
	String label
) {
}
