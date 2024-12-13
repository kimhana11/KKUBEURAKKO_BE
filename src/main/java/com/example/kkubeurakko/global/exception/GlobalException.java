package com.example.kkubeurakko.global.exception;

import com.example.kkubeurakko.global.common.BadResponseMsgEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GlobalException extends RuntimeException{
	private BadResponseMsgEnum globalExceptionCode;
}
