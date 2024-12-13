package com.example.kkubeurakko.api.address.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.kkubeurakko.api.address.controller.request.AddressRequest;
import com.example.kkubeurakko.api.address.controller.response.AddressResponse;
import com.example.kkubeurakko.api.address.mapper.AddressMapper;
import com.example.kkubeurakko.domain.address.Address;
import com.example.kkubeurakko.domain.address.AddressRepository;
import com.example.kkubeurakko.domain.user.User;
import com.example.kkubeurakko.domain.user.UserRepository;
import com.example.kkubeurakko.exception.ApiException;
import com.example.kkubeurakko.global.common.BadResponseMsgEnum;
import com.example.kkubeurakko.global.oauth.dto.CustomOAuth2User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {
	private final AddressRepository addressRepository;
	private final UserRepository userRepository;
	private final AddressMapper addressMapper;

	//모든 배송지 조회메서드
	public List<AddressResponse> findAddressAll(CustomOAuth2User customOAuth2User){
		User user = findUser(customOAuth2User);
		List<Address> addressList = addressRepository.findAllByUser(user)
			.orElseThrow(()-> new ApiException(BadResponseMsgEnum.ADDRESS_NULL));

		List<AddressResponse> addressResponseList = addressMapper.addressListToAddressResponseList(addressList);
		return addressResponseList;
	}

	//배송지 저장 메서드
	public void saveAddress(CustomOAuth2User customOAuth2User, AddressRequest addressRequest){
		User user = findUser(customOAuth2User);
		if(addressRequest.isPrimary()){
			updatePrimary(user);
		}
		Address address = addressMapper.addressRequestToAddress(addressRequest, user);
		addressRepository.save(address);
	}

	//배송지 수정 메서드
	@Transactional
	public void updateAddress(CustomOAuth2User customOAuth2User, Long addressId, AddressRequest addressRequest){
		Address address = addressRepository.findById(addressId)
			.orElseThrow(()-> new ApiException(BadResponseMsgEnum.ADDRESS_NULL));
		User user = findUser(customOAuth2User);

		if(addressRequest.isPrimary() && !address.getIsPrimary()){
			updatePrimary(user);
		}
		address.updateAddress(addressId, addressRequest);
	}

	public void deleteAddress(Long addressId){
		if(!addressRepository.existsById(addressId)){
			throw new ApiException(BadResponseMsgEnum.ADDRESS_NULL);
		}
		addressRepository.deleteById(addressId);
	}

	//새로 저장하는 주소가 기본주소로 설정한다면 기존 기본주소를 해제
	//트랜잭션 범위에 둠으로써 영속성 컨테이너에 포함시켜 필요한 필드만 더티체킹을 통해 업데이트
	//트랜잭션을 최대한 짧게 가져가기 위해
	@Transactional(propagation = Propagation.REQUIRED)
	public void updatePrimary(User user){
		Address address = addressRepository.findPrimaryAddressByUser(user)
			.orElseThrow(()->new ApiException(BadResponseMsgEnum.ADDRESS_NULL));
		address.updatePrimary(!address.getIsPrimary());
	}

	private User findUser(CustomOAuth2User customOAuth2User){
		String userNumber = customOAuth2User.getUserNumber();
		return userRepository.findByUserNumber(userNumber);
	}
}
