package com.example.kkubeurakko.global.oauth.dto;

import java.util.Map;

public class KakaoResponse implements OAuth2Response{
	private final Map<String, Object> attribute;
	public KakaoResponse(Map<String, Object> attribute){
		this.attribute = (Map<String, Object>) attribute.get("response");
	}
	@Override
	public String getProvider(){
		return "kakao";
	}

	@Override
	public String getProviderId() {
		return attribute.get("id").toString();
	}

	@Override
	public String getEmail() {
		return attribute.get("email").toString();
	}

	@Override
	public String getNickname() {
		return attribute.get("nickname").toString();
	}
}
