package com.example.kkubeurakko.api.address.controller.response;

public record AddressResponse (
	Long id,
	String addressNickname,
	String postalCode,
	String roadName,
	String detailedAddress,
	boolean isPrimary
){
}
