package com.example.kkubeurakko.api.controller.address;

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

import com.example.kkubeurakko.api.controller.address.request.AddressRequest;
import com.example.kkubeurakko.api.controller.address.response.AddressResponse;
import com.example.kkubeurakko.api.service.address.AddressService;
import com.example.kkubeurakko.global.common.CommonResponse;
import com.example.kkubeurakko.global.common.ResponseMsgEnum;
import com.example.kkubeurakko.global.oauth.dto.CustomOAuth2User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/addresses")
public class AddressController {
	private final AddressService addressService;

	// 모든 주소 조회 API
	@GetMapping("")
	public ResponseEntity<List<AddressResponse>> getAllAddresses(
		@AuthenticationPrincipal CustomOAuth2User customOAuth2User
	) {
		List<AddressResponse> addressList = addressService.findAddressAll(customOAuth2User);
		return ResponseEntity.status(HttpStatus.OK).body(addressList);
	}

	// 기본 주소 조회 API
	@GetMapping("/primary")
	public ResponseEntity<AddressResponse> getPrimaryAddress(
		@AuthenticationPrincipal CustomOAuth2User customOAuth2User
	) {
		AddressResponse addressResponse = addressService.findPrimaryAddress(customOAuth2User);
		return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
	}

	// 주소 추가 API
	@PostMapping("")
	public ResponseEntity<CommonResponse> createAddress(
		@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
		@Valid @RequestBody AddressRequest addressRequest
	) {
		addressService.saveAddress(customOAuth2User, addressRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(
			new CommonResponse(
				ResponseMsgEnum.SAVE_ADDRESS_SUCCESS.getCode(),
				ResponseMsgEnum.SAVE_ADDRESS_SUCCESS.getResponseMsg(),
				null
			)
		);
	}

	// 주소 수정 API
	@PutMapping("/{addressId}")
	public ResponseEntity<CommonResponse> updateAddress(
		@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
		@PathVariable(name = "addressId") Long addressId,
		@Valid @RequestBody AddressRequest addressRequest
	) {
		addressService.updateAddress(customOAuth2User, addressId, addressRequest);
		return ResponseEntity.status(HttpStatus.OK).body(
			new CommonResponse(
				ResponseMsgEnum.UPDATE_ADDRESS_SUCCESS.getCode(),
				ResponseMsgEnum.UPDATE_ADDRESS_SUCCESS.getResponseMsg(),
				null
			)
		);
	}

	// 주소 삭제 API
	@DeleteMapping("/{addressId}")
	public ResponseEntity<CommonResponse> deleteAddress(@PathVariable(name = "addressId") Long addressId) {
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
