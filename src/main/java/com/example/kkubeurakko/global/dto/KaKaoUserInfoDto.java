package com.example.kkubeurakko.global.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor //역직렬화를 위한 기본 생성자
@JsonIgnoreProperties(ignoreUnknown = true)
public class KaKaoUserInfoDto {
	@JsonProperty("id")
	public Long id;

	@JsonProperty("kakao_account")
	public KakaoAccount kakaoAccount;

	//uuid 등 추가 정보
	@JsonProperty("for_partner")
	public Partner partner;

	@Getter
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class KakaoAccount {
		//이메일이 유효 여부
		// true : 유효한 이메일, false : 이메일이 다른 카카오 계정에 사용돼 만료
		@JsonProperty("is_email_valid")
		public Boolean isEmailValid;

		//이메일이 인증 여부
		//true : 인증된 이메일, false : 인증되지 않은 이메일
		@JsonProperty("is_email_verified")
		public Boolean isEmailVerified;

		//카카오계정 대표 이메일
		@JsonProperty("email")
		public String email;
	}
	@Getter
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Partner {
		//고유 ID
		@JsonProperty("uuid")
		public String uuid;
	}
}
