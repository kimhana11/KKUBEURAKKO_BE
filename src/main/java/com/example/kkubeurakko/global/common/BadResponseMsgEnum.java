package com.example.kkubeurakko.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BadResponseMsgEnum {
	//조회시 null 506

	//
	USER_NOT_FOUND(506, "조회된 유저가 없습니다."),

	//주소 관련 응답
	ADDRESS_NOT_FOUND(506, "조회된 주소가 없습니다."),

	//인증번호 응답
	SMS_CERTIFICATION_CODE_MISMATCH(400, "인증번호가 일치하지 않습니다."),
	SMS_CERTIFICATION_CODE_EXPIRED(402, "인증번호 제한시간이 만료되었습니다."),
	SMS_NOT_ENOUGH_BALANCE(500, "서버에러"),
	//
	JWT_ACCESS_EXPIRED(401, "access토큰이 만료되었습니다."),
	JWT_ACCESS_NULL(401, "access토큰이 없습니다."),
	JWT_REFRESH_EXPIRED(500, "refresh토큰이 만료되었습니다."),
	JWT_REFRESH_NULL(500, "refresh토큰이 없습니다."),
	COOKIE_NULL(500, "전달된 쿠키가 없습니다.");

	private int code;
	private String responseMsg;
}
