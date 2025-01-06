package com.example.kkubeurakko.global.api.service.sms;

import org.springframework.stereotype.Service;

import com.example.kkubeurakko.global.api.controller.sms.request.SmsRequest;
import com.example.kkubeurakko.global.api.controller.sms.request.SmsVerify;
import com.example.kkubeurakko.global.api.exception.SmsVerifyException;
import com.example.kkubeurakko.global.api.repository.SmsRepository;
import com.example.kkubeurakko.global.security.sms.SmsCertificationUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SmsService {
	private final SmsCertificationUtil smsCertificationUtil;
	private final SmsRepository smsRepository;

	public void SendSms(SmsRequest smsRequest){
		String phoneNum = smsRequest.phoneNum();
		String certificationCode = Integer.toString((int)(Math.random() * (999_999 - 100_000 + 1))+ 100_000);
		smsCertificationUtil.sendSMS(phoneNum, certificationCode);
		smsRepository.createSmsCertification(phoneNum, certificationCode);
	}

	public void verifyCode(SmsVerify smsVerify){
		if(isVerify(smsVerify.phoneNum(), smsVerify.certificationCode())){
			smsRepository.deleteSmsCertification(smsVerify.phoneNum());
			log.info("sms 인증번호 삭제");
		} else{
			log.error("sms 인증 실패");
			throw new SmsVerifyException();
		}
	}

	private boolean isVerify(String phoneNum, String certificationCode){
		return smsRepository.hasKey(phoneNum) &&
			smsRepository.getSmsCertification(phoneNum).equals(certificationCode);
	}
}
