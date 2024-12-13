package com.example.kkubeurakko.api.address.controller.response;

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
