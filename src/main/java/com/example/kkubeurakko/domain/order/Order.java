package com.example.kkubeurakko.domain.order;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.review.Review;
import com.example.kkubeurakko.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private LocalDateTime estimatedCompletionTime; // 예상 완료 시간
    private LocalDateTime receivedTime; // 주문을 받은 시간 (사장님이 확인한 시간)

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // 주문에 대한 리뷰 일대일
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Review review;
}
