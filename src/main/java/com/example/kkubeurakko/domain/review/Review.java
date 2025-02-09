package com.example.kkubeurakko.domain.review;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.order.Order;
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
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @NotNull
    @Column(name = "rate", nullable = false, columnDefinition = "double default 0.0")
    private double rate;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false, unique = true) //주문 필수, 한 리뷰당 한 주문
    private Order order;


    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewImage> images = new ArrayList<>();
}
