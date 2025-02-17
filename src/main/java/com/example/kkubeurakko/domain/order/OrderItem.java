package com.example.kkubeurakko.domain.order;


import com.example.kkubeurakko.domain.menu.Menu;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id", updatable = false)
    private Long id;

    @NotNull
    @Column(name = "quantity")
    private int quantity;

    @NotNull
    @Column(name = "total_price", precision = 10, scale = 0)
    private BigDecimal totalPrice;

    @NotNull
    @ElementCollection
    @Column(name = "selected_option_id")
    private List<Long> selectedOptionIds = new ArrayList<>(); // 선택된 옵션 ID 리스트

    @NotNull
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
