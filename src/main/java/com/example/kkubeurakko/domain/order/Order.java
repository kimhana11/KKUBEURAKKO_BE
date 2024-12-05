package com.example.kkubeurakko.domain.order;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.review.Review;
import com.example.kkubeurakko.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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
    private LocalDateTime estimatedCompletionTime;
    private String storeRequests;
    private String deliveryInstructions;
    private String addressLine;
    private String postalCode;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod; // 결제 수단

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Review review;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guest_order_id", nullable = true)
    private GuestOrder guestOrder;

    @Builder
    public Order(BigDecimal totalAmount,LocalDateTime orderDate, String storeRequests,String deliveryInstructions,
                 String addressLine,String postalCode, PaymentMethod paymentMethod, User user, List<OrderItem> orderItems,GuestOrder guestOrder){
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.storeRequests =  storeRequests;
        this.deliveryInstructions = deliveryInstructions;
        this.addressLine = addressLine;
        this.postalCode = postalCode;
        this.paymentMethod = paymentMethod;
        this.user =user;
        this.orderItems = orderItems;
        this.guestOrder = guestOrder;

    }
    // 주문 상태 변경 메서드
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public void setEstimatedCompletionTime(LocalDateTime estimatedCompletionTime){
        this.estimatedCompletionTime = estimatedCompletionTime;
    }
}
