package com.example.kkubeurakko.api.address.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressRequest (

	@NotNull(message = "서버 에러") Boolean isPrimary,
	@NotBlank(message = "배송지명은 공백일 수 없습니다.") String addressNickname,
	@NotBlank(message = "주소는 공백없이 작성해야 합니다.") String postalCode,
	@NotBlank(message = "주소는 공백없이 작성해야 합니다.") String roadName,
	@NotBlank(message = "주소는 공백없이 작성해야 합니다.") String detailedAddress
){
}
