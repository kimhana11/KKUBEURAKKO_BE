package com.example.kkubeurakko.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMsgEnum {
	SAVE_ADDRESS_SUCCESS(200, "추가되었습니다."),
	UPDATE_ADDRESS_SUCCESS(200, "수정되었습니다."),
	DELETE_ADDRESS_SUCCESS(200, "삭제되었습니다."),
	SEND_CERTIFICATION_CODE_SUCCESS(200, "인증번호가 발송되었습니다."),
	VERIFY_CERTIFICATION_CODE_SUCCESS(200, "인증되었습니다."),
	FIND_MENU_IMG_LIST_SUCCESS(200, "메뉴 이미지 리스트 조회 성공");
	private int code;
	private String responseMsg;
}
