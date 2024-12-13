package com.example.kkubeurakko.exception;

import com.example.kkubeurakko.global.common.BadResponseMsgEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException{
	private BadResponseMsgEnum apiExceptionCode;
}
