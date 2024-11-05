package com.example.kkubeurakko.domain.coupon;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CouponConditionType {
    MIN_ORDER_AMOUNT("최소 주문 금액"),
    SPECIFIC_MENU_ITEMS("특정 메뉴 주문시"),
    FIRST_ORDER_DISCOUNT("첫 주문시"),
    NO_CONDITIONS("조건없음");

    private final String description;

}