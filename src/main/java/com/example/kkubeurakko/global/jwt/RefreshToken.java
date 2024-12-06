package com.example.kkubeurakko.global.jwt;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class RefreshToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userNumber;		//유저 식별자
	private String refresh;			//토큰
	private String expiration;		//만료시간

	@Builder
	RefreshToken(String userNumber, String refresh, String expiration){
		this.userNumber = userNumber;
		this.refresh = refresh;
		this.expiration = expiration;
	}
}
