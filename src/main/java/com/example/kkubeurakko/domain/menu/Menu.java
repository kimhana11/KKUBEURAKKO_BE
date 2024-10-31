package com.example.kkubeurakko.domain.menu;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.menuOption.MenuOption;
import com.example.kkubeurakko.domain.order.OrderItem;
import com.example.kkubeurakko.domain.order.Order;
import com.example.kkubeurakko.domain.review.Review;
import com.example.kkubeurakko.domain.store.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //메뉴 이름
    private BigDecimal price; //가격
    private String description; //메뉴 설명
    private String imageUrl; //메뉴 이미지

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<MenuOption> options = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;


    @OneToMany(mappedBy = "menu")
    private List<OrderItem> orderItems = new ArrayList<>(); // 주문 항목 목록

    //메뉴에 달린 모든 리뷰 조회
    public List<Review> getReviews() {
        return orderItems.stream()
                .map(OrderItem::getOrder)
                .filter(order -> order.getReview() != null)
                .map(Order::getReview)
                .collect(Collectors.toList());
    }

}
