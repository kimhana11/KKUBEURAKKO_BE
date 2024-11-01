package com.example.kkubeurakko.domain.cart;

import com.example.kkubeurakko.domain.menu.Menu;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
    private int quantity;

    @ElementCollection
    private List<Long> selectedOptionIds = new ArrayList<>(); // 선택된 옵션 ID 리스트

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

}