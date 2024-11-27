package com.example.kkubeurakko.global.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
	private String role;
	private String nickname;
	private String email;
	private String userNumber;
}
