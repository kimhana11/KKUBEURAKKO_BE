package com.example.kkubeurakko.api.address.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.kkubeurakko.api.address.controller.request.AddressRequest;
import com.example.kkubeurakko.api.address.controller.response.AddressResponse;
import com.example.kkubeurakko.domain.address.Address;
import com.example.kkubeurakko.domain.user.User;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	AddressResponse addressToAddressResponse(Address address);
	Address addressRequestToAddress(AddressRequest addressRequest, User user);
	List<AddressResponse> addressListToAddressResponseList(List<Address> addressList);
}
