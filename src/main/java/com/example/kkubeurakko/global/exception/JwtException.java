package com.example.kkubeurakko.global.exception;

import com.example.kkubeurakko.global.common.ResponseMsgEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtException extends RuntimeException{
	private ResponseMsgEnum jwtExceptionCode;
}
