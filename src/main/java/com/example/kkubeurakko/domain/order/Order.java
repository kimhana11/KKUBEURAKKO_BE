package com.example.kkubeurakko.domain.order;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.review.Review;
import com.example.kkubeurakko.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

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
    private LocalDateTime estimatedCompletionTime;
    private String storeRequests;
    private String deliveryInstructions;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod; // 결제 수단

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Review review;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guest_order_id", nullable = true)
    private GuestOrder guestOrder;

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public void setEstimatedCompletionTime(LocalDateTime estimatedCompletionTime){
        this.estimatedCompletionTime = estimatedCompletionTime;
    }
}
