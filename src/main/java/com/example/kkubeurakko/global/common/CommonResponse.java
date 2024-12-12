package com.example.kkubeurakko.global.common;

import org.springframework.http.HttpStatus;

public record CommonResponse(
	int code,
	String message,
	Object data
) {
}
