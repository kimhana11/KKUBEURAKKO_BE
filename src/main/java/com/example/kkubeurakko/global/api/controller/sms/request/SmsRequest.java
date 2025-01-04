package com.example.kkubeurakko.global.api.controller.sms.request;

import jakarta.validation.constraints.NotBlank;

public record SmsRequest(
	@NotBlank(message = "휴대폰 번호를 입력해주세요")
	String phoneNum
) {
}
