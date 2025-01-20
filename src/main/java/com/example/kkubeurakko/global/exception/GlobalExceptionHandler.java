package com.example.kkubeurakko.global.exception;

import java.net.http.HttpResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.kkubeurakko.global.common.BadResponseMsgEnum;
import com.example.kkubeurakko.global.common.CommonResponse;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;

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
				HttpStatus.BAD_REQUEST.value(),
				methodArgumentNotValidException.getMessage(),
				null
			)
		);
	}

	@ExceptionHandler({Exception.class})
	protected ResponseEntity<CommonResponse> IntervalExceptionHandler(Exception exception){
		log.error(exception.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
			new CommonResponse(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
				null
			)
		);
	}
}
