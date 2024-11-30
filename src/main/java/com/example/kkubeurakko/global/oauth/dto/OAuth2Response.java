package com.example.kkubeurakko.global.oauth.dto;

public interface OAuth2Response {
	String getProvider(); //제공자(google, kakao, naver, ...)
	String getProviderId(); // 제공자에서 발급해준 아이디
	String getEmail();		// 이메일
	String getNickname();		// 닉네임
}
