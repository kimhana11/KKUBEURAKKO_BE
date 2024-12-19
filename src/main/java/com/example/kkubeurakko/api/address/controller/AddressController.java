package com.example.kkubeurakko.api.address.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kkubeurakko.api.address.controller.request.AddressRequest;
import com.example.kkubeurakko.api.address.controller.response.AddressResponse;
import com.example.kkubeurakko.api.address.service.AddressService;
import com.example.kkubeurakko.global.common.CommonResponse;
import com.example.kkubeurakko.global.common.ResponseMsgEnum;
import com.example.kkubeurakko.global.oauth.dto.CustomOAuth2User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {
	private final AddressService addressService;

	//모든 주소 찾는 api
	@GetMapping("")
	public ResponseEntity<List<AddressResponse>> findAddressAll(
		@AuthenticationPrincipal CustomOAuth2User customOAuth2User
	){
		List<AddressResponse> addressList = addressService.findAddressAll(customOAuth2User);
		return ResponseEntity.status(HttpStatus.OK).body(addressList);
	}

	// 주소 저장 api
	@PostMapping("")
	public ResponseEntity<CommonResponse> saveAddress(
		@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
		@RequestBody AddressRequest addressRequest
	){
		addressService.saveAddress(customOAuth2User, addressRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(
			new CommonResponse(
				ResponseMsgEnum.SAVE_ADDRESS_SUCCESS.getCode(),
				ResponseMsgEnum.SAVE_ADDRESS_SUCCESS.getResponseMsg(),
				null
			)
		);
	}

	// 주소 수정 api
	@PutMapping("/{addressId}")
	public ResponseEntity<CommonResponse> updateAddress(
		@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
		@PathVariable(name = "addressId") Long addressId,
		@RequestBody AddressRequest addressRequest
	){
		addressService.updateAddress(customOAuth2User, addressId, addressRequest);
		return ResponseEntity.status(HttpStatus.OK).body(
			new CommonResponse(
				ResponseMsgEnum.UPDATE_ADDRESS_SUCCESS.getCode(),
				ResponseMsgEnum.UPDATE_ADDRESS_SUCCESS.getResponseMsg(),
				null
			)
		);
	}

	@DeleteMapping("/{addressId}")
	public ResponseEntity<CommonResponse> deleteAddress(@PathVariable(name = "addressId") Long addressId){
		addressService.deleteAddress(addressId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
			new CommonResponse(
				ResponseMsgEnum.DELETE_ADDRESS_SUCCESS.getCode(),
				ResponseMsgEnum.DELETE_ADDRESS_SUCCESS.getResponseMsg(),
				null
			)
		);
	}
}
