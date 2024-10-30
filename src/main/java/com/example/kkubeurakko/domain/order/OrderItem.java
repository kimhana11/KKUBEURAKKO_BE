package com.example.kkubeurakko.domain.order;

import com.example.kkubeurakko.domain.menu.Menu;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ElementCollection
    private List<Long> selectedOptionIds = new ArrayList<>(); // 선택된 옵션 ID 리스트

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private BigDecimal totalPrice;
}
