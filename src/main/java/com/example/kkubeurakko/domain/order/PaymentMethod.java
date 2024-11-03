package com.example.kkubeurakko.domain.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentMethod {
    CREDIT_CARD("카카오페이"),
    DEBIT_CARD("신용카드");

    private final String text;
}