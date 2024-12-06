package com.example.kkubeurakko.api;

import org.springframework.http.HttpStatus;

public record CommonResponse(
	int code,
	HttpStatus httpStatus,
	String message,
	Object data
) {
}
