package com.example.kkubeurakko.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BadResponseMsgEnum {
	//조회시 null 506
	//주소 관련 응답
	ADDRESS_NULL(506, "조회된 주소가 없습니다."),

	//
	JWT_ACCESS_EXPIRED(401, "access토큰이 만료되었습니다."),
	JWT_ACCESS_NULL(401, "access토큰이 없습니다."),
	JWT_REFRESH_EXPIRED(500, "refresh토큰이 만료되었습니다."),
	JWT_REFRESH_NULL(500, "refresh토큰이 없습니다."),
	COOKIE_NULL(500, "전달된 쿠키가 없습니다.");

	private int code;
	private String responseMsg;
}
