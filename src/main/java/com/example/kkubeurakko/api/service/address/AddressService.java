package com.example.kkubeurakko.api.service.address;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kkubeurakko.api.controller.address.request.AddressRequest;
import com.example.kkubeurakko.api.controller.address.response.AddressResponse;
import com.example.kkubeurakko.api.exception.address.AddressNotFoundException;
import com.example.kkubeurakko.api.service.address.mapper.AddressMapper;
import com.example.kkubeurakko.api.exception.user.UserNotFoundException;
import com.example.kkubeurakko.api.service.address.transaction.AddressTransactionService;
import com.example.kkubeurakko.domain.address.Address;
import com.example.kkubeurakko.domain.address.AddressRepository;
import com.example.kkubeurakko.domain.user.User;
import com.example.kkubeurakko.domain.user.UserRepository;
import com.example.kkubeurakko.global.oauth.dto.CustomOAuth2User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {
	private final AddressRepository addressRepository;
	private final UserRepository userRepository;
	private final AddressMapper addressMapper;
	private final AddressTransactionService addressTransactionService;

	//모든 배송지 조회메서드
	public List<AddressResponse> findAddressAll(CustomOAuth2User customOAuth2User){
		User user = findUser(customOAuth2User);
		List<Address> addressList = addressRepository.findAllByUser(user);
		if(addressList == null){
			return null;
		}
		return addressMapper.addressListToAddressResponseList(addressList);
	}

	//기본 배송지 조회 메서드
	//주문 창에서 사용하도록 구현
	public AddressResponse findPrimaryAddress(CustomOAuth2User customOAuth2User){
		User user = findUser(customOAuth2User);
		Address address = addressRepository.findPrimaryAddressByUser(user)
			.or(() -> addressRepository.findFirstByUserOrderById(user))
			.orElseThrow(() -> new AddressNotFoundException());

		return addressMapper.addressToAddressResponse(address);
	}

	//배송지 저장 메서드
	public void saveAddress(CustomOAuth2User customOAuth2User, AddressRequest addressRequest){
		User user = findUser(customOAuth2User);
		if(addressRepository.existsByUser(user) && addressRequest.isPrimary()){
			addressTransactionService.updatePrimary(user);
		}
		Address address = addressMapper.addressRequestToAddress(addressRequest, user);
		addressRepository.save(address);
	}

	//배송지 수정 메서드
	@Transactional
	public void updateAddress(CustomOAuth2User customOAuth2User, Long addressId, AddressRequest addressRequest){
		Address address = addressRepository.findById(addressId)
			.orElseThrow(()-> new AddressNotFoundException());
		User user = findUser(customOAuth2User);

		if(addressRequest.isPrimary() && !address.getIsPrimary()){
			addressTransactionService.updatePrimary(user);
			log.info("기본 주소 수정 완료 : {}", user.getId());
		}
		address.updateAddress(addressId, addressRequest);
		log.info("주소 수정 완료 : {}", addressId);
	}

	public void deleteAddress(Long addressId){
		if(!addressRepository.existsById(addressId)){
			throw new AddressNotFoundException();
		}
		addressRepository.deleteById(addressId);
		log.info("주소 삭제 완료 : {}", addressId);
	}

	private User findUser(CustomOAuth2User customOAuth2User){
		String userNumber = customOAuth2User.getUserNumber();
		return userRepository.findByUserNumber(userNumber)
			.orElseThrow(()-> new UserNotFoundException());
	}
}
