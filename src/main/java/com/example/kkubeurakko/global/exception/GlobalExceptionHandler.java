package com.example.kkubeurakko.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.kkubeurakko.global.common.CommonResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler({GlobalException.class})
	protected ResponseEntity<CommonResponse> globalExceptionHandler(GlobalException globalException){
		log.error(globalException.getGlobalExceptionCode().getResponseMsg());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
			new CommonResponse(
				globalException.getGlobalExceptionCode().getCode(),
				globalException.getGlobalExceptionCode().getResponseMsg(),
				null
			)
		);
	}
	@ExceptionHandler({MethodArgumentNotValidException.class})
	protected ResponseEntity<CommonResponse> validationExceptionHandler(
		MethodArgumentNotValidException methodArgumentNotValidException
	){
		log.error(methodArgumentNotValidException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
			new CommonResponse(
				400,
				methodArgumentNotValidException.getMessage(),
				null
			)
		);
	}
}
