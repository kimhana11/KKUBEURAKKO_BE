package com.example.kkubeurakko.global.oauth.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User {
	private final UserDto userDto;
	public CustomOAuth2User(UserDto userDto){
		this.userDto = userDto;
	}
	@Override
	public Map<String, Object> getAttributes() {
		return Map.of(
			"email", userDto.getEmail(),
			"nickname", userDto.getNickname(),
			"userNumber", userDto.getUserNumber(),
			"role", userDto.getRole().toString()
		);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of((GrantedAuthority) () -> userDto.getRole().toString());
	}

	@Override
	public String getName() {
		return userDto.getNickname();
	}
	public String getEmail(){
		return userDto.getEmail();
	}
	public String getUserNumber(){
		return userDto.getUserNumber();
	}
}
