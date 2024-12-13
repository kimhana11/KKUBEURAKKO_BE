package com.example.kkubeurakko.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.kkubeurakko.global.common.CommonResponse;

@RestControllerAdvice(basePackages = "com.example.kkubeurakko.api")
public class GlobalExceptionHandler {
	@ExceptionHandler({GlobalException.class})
	protected ResponseEntity<CommonResponse> apiExceptionHandler(GlobalException globalException){
		return ResponseEntity.internalServerError().body(
			new CommonResponse(
				globalException.getGlobalExceptionCode().getCode(),
				globalException.getGlobalExceptionCode().getResponseMsg(),
				null
			)
		);
	}
}
