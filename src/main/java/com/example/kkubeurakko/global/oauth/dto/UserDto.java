package com.example.kkubeurakko.global.oauth.dto;

import com.example.kkubeurakko.domain.user.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private UserRole role;
	private String nickname;
	private String email;
	private String userNumber;

	public UserDto(String userNumber, UserRole role){
		this.userNumber = userNumber;
		this.role = role;
	}
}
