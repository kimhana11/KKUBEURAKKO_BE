package com.example.kkubeurakko.domain.coupon;

import lombok.Getter;

@Getter
public enum CouponConditionType {
    MIN_ORDER_AMOUNT("최소 주문 금액"),
    SPECIFIC_MENU_ITEMS("특정 메뉴 필요");

    private final String description;

    CouponConditionType(String description) {
        this.description = description;
    }
}