package com.example.kkubeurakko.api.address.exception;

import com.example.kkubeurakko.global.common.BadResponseMsgEnum;
import com.example.kkubeurakko.global.exception.GlobalException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddressNotFoundException extends GlobalException {
	public AddressNotFoundException(){
		super(BadResponseMsgEnum.ADDRESS_NOT_FOUND);
	}
}
