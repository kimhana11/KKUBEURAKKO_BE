package com.example.kkubeurakko.api.user;

import com.example.kkubeurakko.global.common.BadResponseMsgEnum;
import com.example.kkubeurakko.global.exception.GlobalException;

public class UserNotFoundException extends GlobalException {
	public UserNotFoundException(){
		super(BadResponseMsgEnum.USER_NOT_FOUND);
	}
}
