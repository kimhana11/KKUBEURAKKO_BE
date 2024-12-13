package com.example.kkubeurakko.api.address.controller.request;

public record AddressRequest (

	boolean isPrimary,
	String postalCode,
	String roadName,
	String detailedAddress
){
}
