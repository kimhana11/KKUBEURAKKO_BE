package com.example.kkubeurakko.domain.coupon;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CouponType {
    PERCENTAGE_DISCOUNT("% 할인"),
    FIXED_AMOUNT_DISCOUNT("금액 차감"),
    FREE_SHIPPING("배달비 무료"),
    BEVERAGE_SIZE_UPGRADE ("음료 사이즈업");


    private final String text;
}