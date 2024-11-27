package com.example.kkubeurakko.global.oauth.dto;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustumOAuth2User implements OAuth2User {
	private final UserDto userDto;
	public CustumOAuth2User(UserDto userDto){
		this.userDto = userDto;
	}
	@Override
	public Map<String, Object> getAttributes() {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}
}
