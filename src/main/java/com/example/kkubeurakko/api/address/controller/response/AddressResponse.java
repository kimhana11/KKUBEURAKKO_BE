package com.example.kkubeurakko.api.address.service.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public record AddressResponse (
	Long id,
	String postalCode,
	String roadName,
	String detailedAddress,
	boolean isPrimary
){
}
