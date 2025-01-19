package com.example.kkubeurakko.global.api.service.sms;

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

	public void SendSms(SmsRequest smsRequest){
		try{
			String phoneNum = smsRequest.phoneNum();
			String certificationCode = Integer.toString((int)(Math.random() * (999_999 - 100_000 + 1))+ 100_000);
			smsCertificationUtil.sendSMS(phoneNum, certificationCode);
			smsRepository.createSmsCertification(phoneNum, certificationCode);
		} catch (Exception e){
			throw new SmsNotEnoughBalanceException();
		}
	}

	public void verifyCode(SmsVerify smsVerify, CustomOAuth2User customOAuth2User){
		if(isVerify(smsVerify.phoneNum(), smsVerify.certificationCode())){
			smsRepository.deleteSmsCertification(smsVerify.phoneNum());
			userService.saveUserPhone(smsVerify.phoneNum(), customOAuth2User);
			log.info("sms 인증번호 삭제");
		} else{
			log.error("sms 인증 실패");
			throw new SmsVerifyException();
		}
	}

	private boolean isVerify(String phoneNum, String certificationCode){
		if(!smsRepository.hasKey(phoneNum)){
			throw new SmsCertificationCodeExpiredException();
		}
		return smsRepository.getSmsCertification(phoneNum).equals(certificationCode);
	}
}
