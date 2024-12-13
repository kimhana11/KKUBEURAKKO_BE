package com.example.kkubeurakko.api.address.mapper;

import java.util.ArrayList;
import java.util.List;

import com.example.kkubeurakko.api.address.controller.request.AddressRequest;
import com.example.kkubeurakko.api.address.controller.response.AddressResponse;
import com.example.kkubeurakko.domain.address.Address;
import com.example.kkubeurakko.domain.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddressMapperImpl implements AddressMapper{
	@Override
	public AddressResponse addressToAddressResponse(Address address) {
		return new AddressResponse(
			address.getId(),
			address.getPostalCode(),
			address.getRoadName(),
			address.getDetailedAddress(),
			address.getIsPrimary()
		);
	}

	@Override
	public Address addressRequestToAddress(AddressRequest addressRequest, User user) {
		return Address.builder()
			.postalCode(addressRequest.postalCode())
			.roadName(addressRequest.roadName())
			.detailedAddress(addressRequest.detailedAddress())
			.isPrimary(addressRequest.isPrimary())
			.user(user)
			.build();
	}

	@Override
	public List<AddressResponse> addressListToAddressResponseList(List<Address> addressList) {
		List<AddressResponse> addressResponseList = new ArrayList<>();
		for(Address address : addressList){
			AddressResponse addressResponse = new AddressResponse(
				address.getId(),
				address.getPostalCode(),
				address.getRoadName(),
				address.getDetailedAddress(),
				address.getIsPrimary()
			);
			addressResponseList.add(addressResponse);
		}
		return addressResponseList;
	}

}
