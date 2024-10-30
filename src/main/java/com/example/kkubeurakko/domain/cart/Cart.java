package com.example.kkubeurakko.domain.cart;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    //유저와 일대일 매핑
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 장바구니 비우기
    public void clear() {
        items.clear();
    }
}
