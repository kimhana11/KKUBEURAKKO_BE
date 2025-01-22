package com.example.kkubeurakko.global.api.controller.sms.request;

import jakarta.validation.constraints.NotBlank;

public record SmsVerify(
	@NotBlank(message = "휴대폰 번호를 입력해주세요.")
	String phoneNum,
	@NotBlank(message = "인증번호를 입력해주세요.")
	String certificationCode
) {
}
