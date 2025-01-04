package com.example.kkubeurakko.global.api.service.sms;

import org.springframework.stereotype.Service;

import com.example.kkubeurakko.global.api.controller.sms.request.SmsRequest;
import com.example.kkubeurakko.global.security.sms.SmsCertificationUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SmsService {
	private final SmsCertificationUtil smsCertificationUtil;

	public void SendSms(SmsRequest smsRequest){
		String phoneNum = smsRequest.phoneNum();
		String certificationCode = Integer.toString((int)(Math.random() * (999_999 - 100_000 + 1))+ 100_000);
		smsCertificationUtil.sendSMS(phoneNum, certificationCode);
	}
}
