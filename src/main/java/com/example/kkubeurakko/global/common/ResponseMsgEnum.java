package com.example.kkubeurakko.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMsgEnum {

	JWT_REFRESH_EXPIRED(500, "refresh토큰이 만료되었습니다."),
	JWT_REFRESH_NULL(500, "refresh토큰이 없습니다."),
	COOKIE_NULL(500, "전달된 쿠키가 없습니다.");

	private int code;
	private String responseMsg;
}
