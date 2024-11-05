package com.example.kkubeurakko.domain.menu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuLabel {
    BEST_SELLER("베스트"),
    TRENDING("인기"),
    SALE("할인"),
    NEW("신메뉴");

    private final String text;
}