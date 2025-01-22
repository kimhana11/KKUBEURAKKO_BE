package com.example.kkubeurakko.global.api.exception;

import com.example.kkubeurakko.global.common.BadResponseMsgEnum;
import com.example.kkubeurakko.global.exception.GlobalException;

public class SmsVerifyException extends GlobalException {
	public SmsVerifyException(){
		super(BadResponseMsgEnum.SMS_CERTIFICATION_CODE_MISMATCH);
	}
}
