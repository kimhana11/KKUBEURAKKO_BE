package com.example.kkubeurakko.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    ADMIN("관리자",2),
    CUSTOMER("회원",1),
    GUEST("비회원",0);

    private final String text;
    private final int accessLevel;  // 권한 레벨 부여

    public boolean hasAccess(int requiredLevel) {
        return this.accessLevel >= requiredLevel;
    }
}
