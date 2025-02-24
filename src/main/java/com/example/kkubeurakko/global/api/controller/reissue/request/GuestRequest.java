package com.example.kkubeurakko.global.api.controller.reissue.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record GuestRequest(
	@NotBlank(message = "전화번호는 공백일 수 없습니다.")
	@Pattern(regexp = "^(01[016789])-\\d{4}-\\d{4}$|^(0[3-9]\\d)-\\d{4}-\\d{4}$", message = "올바른 전화번호 형식이 아닙니다.")
	@Size(max = 13, min = 13)
	String phoneNum,
	@NotBlank(message = "우편번호는 공백일 수 없습니다.")
	@Pattern(regexp = "^\\d{5}$", message = "우편번호는 5자리의 숫자여야 합니다.")	//5자리의 숫자
	String postalCode,
	@NotBlank(message = "도로명주소는 공백없이 작성해야 합니다.")
	@Size(max = 50, min = 2, message = "도로명주소가 너무 깁니다.")
	String roadName,
	@NotBlank(message = "상세 주소는 공백없이 작성해야 합니다.")
	@Size(max = 20, min = 2, message = "상세 주소는 20글자 이하여야 합니다.")
	String detailedAddress
) {
}
