package com.example.kkubeurakko.api.address.controller.request;

public record AddressRequest (

	Boolean isPrimary,
	String addressNickname,
	String postalCode,
	String roadName,
	String detailedAddress
){
}
