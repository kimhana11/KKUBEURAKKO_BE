package com.example.kkubeurakko.domain.menu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuCategory {

    CHICKEN("치킨"),
    SIDE("사이드 메뉴"),
    BEVERAGE("음료수"),
    SAUCE("소스류"),
    PACKAGE("세트 메뉴"),
    OTHER  ("기타");

    private final String text;
}
