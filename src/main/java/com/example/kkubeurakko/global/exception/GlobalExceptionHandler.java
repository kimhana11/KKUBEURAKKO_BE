package com.example.kkubeurakko.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.kkubeurakko.global.common.CommonResponse;

@RestControllerAdvice(basePackages = "com.example.kkubeurakko.api")
public class ApiExceptionHandler {
	@ExceptionHandler({ApiException.class})
	protected ResponseEntity<CommonResponse> apiExceptionHandler(ApiException apiException){
		return ResponseEntity.internalServerError().body(
			new CommonResponse(
				apiException.getApiExceptionCode().getCode(),
				apiException.getApiExceptionCode().getResponseMsg(),
				null
			)
		);
	}
}
