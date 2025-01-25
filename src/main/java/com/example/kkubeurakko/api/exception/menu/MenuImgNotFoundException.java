package com.example.kkubeurakko.api.exception.menu;

import com.example.kkubeurakko.global.common.BadResponseMsgEnum;
import com.example.kkubeurakko.global.exception.GlobalException;

public class MenuNotFoundException extends GlobalException {
	public MenuNotFoundException() {
		super(BadResponseMsgEnum.FILE_NOT_FOUND);
	}
}
