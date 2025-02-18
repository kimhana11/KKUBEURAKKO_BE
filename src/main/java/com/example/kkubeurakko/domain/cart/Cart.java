package com.example.kkubeurakko.domain.cart;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @NotNull
    @OneToOne
    @JoinColumn(name = "id", unique = true, updatable = false)
    private User user;

    // 장바구니 비우기
    public void clear() {
        items.clear();
    }

    // 장바구니에 아이템 추가
    public void addItem(CartItem item) {
        items.add(item);
    }
}
