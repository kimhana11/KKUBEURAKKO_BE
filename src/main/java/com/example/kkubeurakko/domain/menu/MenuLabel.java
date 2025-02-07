package com.example.kkubeurakko.domain.menu;

import com.example.kkubeurakko.api.exception.menu.MenuLabelNotFoundException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuLabel {
    TRENDING("인기"),
    NEW("신메뉴");
    private final String text;
}