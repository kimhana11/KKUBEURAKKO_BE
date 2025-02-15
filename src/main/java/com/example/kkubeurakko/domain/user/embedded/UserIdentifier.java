package com.example.kkubeurakko.domain.user.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserIdentifier {
	@Column(name = "userNumber", unique = true, nullable = false)
	private String userNumber; //사용자 정보 제공자 + 제공 id 숫자

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "phone", unique = true, length = 11)
	private String phone;

	@Column(name = "nickname", unique = true, nullable = false)
	private String nickname;

	public UserIdentifier(String userNumber, String email, String nickname){
		this.userNumber = userNumber;
		this.email = email;
		this.nickname = nickname;
	}

	public void updateNickname(String nickname){
		this.nickname = nickname;
	}

	public void updateUserPhone(String phone){
		this.phone = phone;
	}
}
