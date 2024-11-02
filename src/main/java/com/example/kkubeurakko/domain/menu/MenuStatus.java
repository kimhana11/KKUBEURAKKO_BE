package com.example.kkubeurakko.domain.menu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuStatus {
    BEST_SELLER("베스트 메뉴"),
    TRENDING("조회수 상승"),
    NEW("신메뉴");

    private final String description;
}