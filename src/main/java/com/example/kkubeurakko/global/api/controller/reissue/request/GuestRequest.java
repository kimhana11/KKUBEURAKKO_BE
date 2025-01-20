package com.example.kkubeurakko.global.api.controller.reissue.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record GuestRequest(
	@NotBlank(message = "전화번호는 공백일 수 없습니다.")
	String phoneNum,
	@NotBlank(message = "우편번호는 공백일 수 없습니다.")
	String postalCode,
	@NotBlank(message = "도로명주소는 공백일 수 없습니다.")
	String roadName,
	@NotBlank(message = "상세주소는 공백일 수 없습니다.")
	String detailAddress
) {
}
