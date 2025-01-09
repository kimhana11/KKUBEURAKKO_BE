package com.example.kkubeurakko.global.api.exception;

import com.example.kkubeurakko.global.common.BadResponseMsgEnum;
import com.example.kkubeurakko.global.exception.GlobalException;

public class SmsCertificationCodeExpiredException extends GlobalException {
	public SmsCertificationCodeExpiredException(){
		super(BadResponseMsgEnum.SMS_CERTIFICATION_CODE_EXPIRED);
	}
}
