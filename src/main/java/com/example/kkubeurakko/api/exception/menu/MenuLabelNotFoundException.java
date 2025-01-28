package com.example.kkubeurakko.api.exception.menu;

import com.example.kkubeurakko.global.common.BadResponseMsgEnum;
import com.example.kkubeurakko.global.exception.GlobalException;

public class MenuLabelNotFoundException extends GlobalException {
	public MenuLabelNotFoundException(){
		super(BadResponseMsgEnum.MENU_LABEL_NOT_FOUND);
	}
}
