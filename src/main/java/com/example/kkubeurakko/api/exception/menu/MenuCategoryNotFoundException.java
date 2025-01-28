package com.example.kkubeurakko.api.exception.menu;

import com.example.kkubeurakko.global.common.BadResponseMsgEnum;
import com.example.kkubeurakko.global.exception.GlobalException;

public class MenuCategoryNotFoundException extends GlobalException {
	public MenuCategoryNotFoundException(){
		super(BadResponseMsgEnum.MENU_CATEGORY_NOT_FOUND);
	}
}
