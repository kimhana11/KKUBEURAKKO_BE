package com.example.kkubeurakko.domain.order;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.review.Review;
import com.example.kkubeurakko.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @Column(name = "order_id", updatable = false)
    private Long id;

    @NotNull
    @Column(name = "order_type", nullable = false, length = 20)
    private String orderType;

    @NotNull
    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @NotNull
    @Column(name = "order_date", nullable = false, updatable = false)
    private LocalDateTime orderDate;

    @Column(name = "estimated_completion_time", nullable = true)
    private LocalDateTime estimatedCompletionTime;

    @Column(name = "store_requests", nullable = true, length = 255)
    private String storeRequests;

    @Column(name = "delivery_instructions", nullable = true, length = 255)
    private String deliveryInstructions;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, length = 20)
    private PaymentMethod paymentMethod;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false, length = 20)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "review_id", nullable = true)
    private Review review;

    @OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
    @JoinColumn(name = "guest_order_id", nullable = true)
    private GuestOrder guestOrder;

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public void setEstimatedCompletionTime(LocalDateTime estimatedCompletionTime){
        this.estimatedCompletionTime = estimatedCompletionTime;
    }
}
