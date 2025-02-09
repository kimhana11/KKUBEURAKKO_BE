package com.example.kkubeurakko.api.exception.menu;

import com.example.kkubeurakko.global.common.BadResponseMsgEnum;
import com.example.kkubeurakko.global.exception.GlobalException;

public class MenuImgNotFoundException extends GlobalException {
	public MenuImgNotFoundException() {
		super(BadResponseMsgEnum.FILE_NOT_FOUND);
	}
}
