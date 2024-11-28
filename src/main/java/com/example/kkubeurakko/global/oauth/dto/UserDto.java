package com.example.kkubeurakko.global.oauth.dto;

import com.example.kkubeurakko.domain.user.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
	private UserRole role;
	private String nickname;
	private String email;
	private String userNumber;
}
