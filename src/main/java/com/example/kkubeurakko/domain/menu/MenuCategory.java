package com.example.kkubeurakko.domain.menu;

import com.example.kkubeurakko.api.exception.menu.MenuLabelNotFoundException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuCategory {

    TRENDING("인기"),
    NEW("신 메뉴"),
    SIDE("사이드"),
    BEVERAGE("음료"),
    PACKAGE("세트"),
    CHICKEN("치킨");          //단품

    private final String text;

    public static MenuCategory getMenuCategory(String category){
        if(category.equals(TRENDING.getText())){
            return TRENDING;
        } else if(category.equals(NEW.getText())){
            return NEW;
        } else if (category.equals(SIDE.getText())) {
            return SIDE;
        } else if (category.equals(BEVERAGE.getText())) {
            return BEVERAGE;
        } else if (category.equals(PACKAGE.getText())) {
            return PACKAGE;
        } else if (category.equals(CHICKEN.getText())) {
            return CHICKEN;
        } else{
            throw new MenuLabelNotFoundException();
        }
    }
}
