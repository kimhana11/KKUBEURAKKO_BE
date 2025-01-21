package com.example.kkubeurakko.global.api.service.sms;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import com.example.kkubeurakko.api.service.user.UserService;
import com.example.kkubeurakko.global.api.controller.sms.request.SmsRequest;
import com.example.kkubeurakko.global.api.controller.sms.request.SmsVerify;
import com.example.kkubeurakko.global.api.exception.SmsCertificationCodeExpiredException;
import com.example.kkubeurakko.global.api.exception.SmsNotEnoughBalanceException;
import com.example.kkubeurakko.global.api.exception.SmsVerifyException;
import com.example.kkubeurakko.global.api.repository.SmsRepository;
import com.example.kkubeurakko.global.oauth.dto.CustomOAuth2User;
import com.example.kkubeurakko.global.security.sms.SmsCertificationUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SmsService {
	private final SmsCertificationUtil smsCertificationUtil;
	private final SmsRepository smsRepository;
	private final UserService userService;
	private final int CERTIFICATION_CODE_BOUND = 1_000_000;

	//인증번호 생성 및 redis저장 메서드 호출하는 메서드
	public void SendSms(SmsRequest smsRequest){
		try{
			String phoneNum = smsRequest.phoneNum();
			String certificationCode = generateCertificationCode();
			smsCertificationUtil.sendSMS(phoneNum, certificationCode);
			smsRepository.createSmsCertification(phoneNum, certificationCode);
		} catch (Exception e){
			throw new SmsNotEnoughBalanceException();
		}
	}

	//회원 인증번호 검증 메서드
	public void verifyCodeForMember(SmsVerify smsVerify, CustomOAuth2User customOAuth2User){
		verifyPhone(smsVerify);
		userService.saveUserPhone(smsVerify.phoneNum(), customOAuth2User);
		log.info("사용자 전화번호 저장");
	}

	//비회원 인증번호 검증 메서드
	public void verifyCodeForGuest(SmsVerify smsVerify){
		verifyPhone(smsVerify);
		log.info("비회원 인증 완료");
	}

	private String generateCertificationCode(){
		SecureRandom random = new SecureRandom();
		return String.format("%06d", random.nextInt(CERTIFICATION_CODE_BOUND));
	}

	private void verifyPhone(SmsVerify smsVerify){
		if(!isVerify(smsVerify.phoneNum(), smsVerify.certificationCode())){
			log.error("인증 실패");
			throw new SmsVerifyException();
		}
		smsRepository.deleteSmsCertification(smsVerify.phoneNum());
		log.info("인증 성공");
	}

	private boolean isVerify(String phoneNum, String certificationCode){
		if(!smsRepository.hasKey(phoneNum)){
			throw new SmsCertificationCodeExpiredException();
		}
		return smsRepository.getSmsCertification(phoneNum).equals(certificationCode);
	}
}
