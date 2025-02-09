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
    @Column(name = "order_item_id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @NotNull
    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    // @CollectionTable(name = "order_item_options", joinColumns = @JoinColumn(name = "order_item_id"))
    @ElementCollection
    @Column(name = "selected_option_id", nullable = false)
    private List<Long> selectedOptionIds = new ArrayList<>(); // 선택된 옵션 ID 리스트

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
