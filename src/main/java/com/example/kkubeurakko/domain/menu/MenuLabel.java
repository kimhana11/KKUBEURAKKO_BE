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

    public static MenuLabel getMenuLabel(String label){
        if(label.equals(TRENDING.getText())){
            return TRENDING;
        } else if(label.equals(NEW.getText())){
            return NEW;
        } else{
            throw new MenuLabelNotFoundException();
        }
    }
}