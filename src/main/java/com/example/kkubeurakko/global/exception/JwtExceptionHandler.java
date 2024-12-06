package com.example.kkubeurakko.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.kkubeurakko.global.api.controller.ReissueController;
import com.example.kkubeurakko.global.common.CommonResponse;

@RestControllerAdvice(basePackageClasses = ReissueController.class)
public class JwtExceptionHandler {
	@ExceptionHandler({JwtException.class})
	protected ResponseEntity<CommonResponse> jwtExceptionHandler(JwtException jwtException){
		return ResponseEntity.badRequest().body(
			new CommonResponse(
				jwtException.getJwtExceptionCode().getCode(),
				jwtException.getJwtExceptionCode().getResponseMsg(),
				null
			)
		);
	}
}
