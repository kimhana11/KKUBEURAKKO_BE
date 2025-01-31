package com.example.kkubeurakko.domain.menu;

import com.example.kkubeurakko.api.exception.menu.MenuLabelNotFoundException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuCategory {

    TRENDING("인기"),
    NEW("신메뉴"),
    SIDE("사이드"),
    BEVERAGE("음료"),
    PACKAGE("세트"),
    CHICKEN("치킨");          //단품

    private final String text;

    public static String getMenuCategoryToLowerCase(String category){
        if(category.equals(TRENDING.getText())){
            return TRENDING.toString().toLowerCase();
        } else if(category.equals(NEW.getText())){
            return NEW.toString().toLowerCase();
        } else if (category.equals(SIDE.getText())) {
            return SIDE.toString().toLowerCase();
        } else if (category.equals(BEVERAGE.getText())) {
            return BEVERAGE.toString().toLowerCase();
        } else if (category.equals(PACKAGE.getText())) {
            return PACKAGE.toString().toLowerCase();
        } else if (category.equals(CHICKEN.getText())) {
            return CHICKEN.toString().toLowerCase();
        } else{
            throw new MenuLabelNotFoundException();
        }
    }
}
