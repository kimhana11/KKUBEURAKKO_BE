package com.example.kkubeurakko.domain.review;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.order.Order;
import com.example.kkubeurakko.domain.order.OrderItem;
import com.example.kkubeurakko.domain.user.User;
import jakarta.persistence.*;
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
    private Long id;

    private String content; // 리뷰 내용
    private double rate;//별점

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 리뷰 작성자

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;


    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewImage> images = new ArrayList<>(); // 리뷰 이미지 목록
}
