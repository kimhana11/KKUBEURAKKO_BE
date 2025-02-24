package com.example.kkubeurakko.api.controller.address.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressRequest (

	@NotNull(message = "서버 에러") boolean isPrimary,
	@NotBlank(message = "배송 지명은 필수 입력값입니다.")
	@Size(max = 30, min = 2, message = "배송 지명은 2글자 이상 30글자 이하여야 합니다.")
	String addressNickname,
	@NotBlank(message = "우편번호는 필수 입력값입니다.")
	@Pattern(regexp = "^\\d{5}$", message = "우편번호는 5자리의 숫자여야 합니다.")	//5자리의 숫자
	String postalCode,
	@NotBlank(message = "도로명주소는 필수 입력값입니다.")
	@Size(max = 50, min = 2, message = "도로명주소가 너무 깁니다.")
	String roadName,
	@NotBlank(message = "상세 주소는 필수 입력값입니다.")
	@Size(max = 20, min = 2, message = "상세 주소는 20글자 이하여야 합니다.")
	String detailedAddress
){
}
