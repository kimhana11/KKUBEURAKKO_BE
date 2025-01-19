package com.example.kkubeurakko.global.api.controller.sms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.kkubeurakko.global.api.controller.sms.request.SmsRequest;
import com.example.kkubeurakko.global.api.controller.sms.request.SmsVerify;
import com.example.kkubeurakko.global.api.service.sms.SmsService;
import com.example.kkubeurakko.global.common.CommonResponse;
import com.example.kkubeurakko.global.common.ResponseMsgEnum;
import com.example.kkubeurakko.global.oauth.dto.CustomOAuth2User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SmsController {
	private final SmsService smsService;

	//인증번호 발송 api
	@PostMapping("/api/sms/send")
	public ResponseEntity<CommonResponse> SendSms(
		@RequestBody @Valid SmsRequest smsRequest
	){
		smsService.SendSms(smsRequest);
		return ResponseEntity.status(HttpStatus.OK).body(
			new CommonResponse(
				ResponseMsgEnum.SEND_CERTIFICATION_CODE_SUCCESS.getCode(),
				ResponseMsgEnum.SEND_CERTIFICATION_CODE_SUCCESS.getResponseMsg(),
				null
			)
		);
	}

	//sms 인증번호 검사 api(회원용)
	@PostMapping("/api/sms/verify/member")
	public ResponseEntity<CommonResponse> verifyCodeForMember(
		@RequestBody @Valid SmsVerify smsVerify,
		@AuthenticationPrincipal CustomOAuth2User customOAuth2User
	){
		smsService.verifyCodeForMember(smsVerify, customOAuth2User);
		return ResponseEntity.status(HttpStatus.OK).body(
			new CommonResponse(
				ResponseMsgEnum.VERIFY_CERTIFICATION_CODE_SUCCESS.getCode(),
				ResponseMsgEnum.VERIFY_CERTIFICATION_CODE_SUCCESS.getResponseMsg(),
				null
			)
		);
	}

	//sms 인증번호 검사 api (비회원용)
	@PostMapping("/api/sms/verify/guest")
	public ResponseEntity<CommonResponse> verifyCodeForGuest(
		@RequestBody @Valid SmsVerify smsVerify
	){
		smsService.verifyCodeForGuest(smsVerify);
		return ResponseEntity.status(HttpStatus.OK).body(
			new CommonResponse(
				ResponseMsgEnum.VERIFY_CERTIFICATION_CODE_SUCCESS.getCode(),
				ResponseMsgEnum.VERIFY_CERTIFICATION_CODE_SUCCESS.getResponseMsg(),
				null
			)
		);
	}
}
