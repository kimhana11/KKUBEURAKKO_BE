package com.example.kkubeurakko.global.api.controller.sms.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SmsVerify(
	@NotBlank(message = "휴대폰 번호를 입력해주세요.")
	@Pattern(regexp = "^(01[016789])-\\d{4}-\\d{4}$|^(0[3-9]\\d)-\\d{4}-\\d{4}$", message = "올바른 전화번호 형식이 아닙니다.")
	@Size(max = 13, min = 13)
	String phoneNum,
	@NotBlank(message = "인증번호를 입력해주세요.")
	@Pattern(regexp = "^\\d{6}$", message = "올바른 인증번호 형식이 아닙니다.")
	String certificationCode
) {
}
