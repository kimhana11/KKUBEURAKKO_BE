package com.example.kkubeurakko.global.api.exception;

import com.example.kkubeurakko.global.common.BadResponseMsgEnum;
import com.example.kkubeurakko.global.exception.GlobalException;

public class SmsNotEnoughBalanceException extends GlobalException {
	public SmsNotEnoughBalanceException(){
		super(BadResponseMsgEnum.SMS_CERTIFICATION_CODE_EXPIRED);
	}
}
