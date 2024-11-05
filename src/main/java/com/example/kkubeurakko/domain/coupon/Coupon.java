package com.example.kkubeurakko.domain.coupon;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.store.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal discountAmount;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
    private String code;
    private String content;

    @Enumerated(EnumType.STRING)
    private CouponType couponType;

    @Enumerated(EnumType.STRING)
    private CouponConditionType conditionType;

    private BigDecimal minOrderAmount; //최소 주문 금액
    private String requiredMenuItems; //특정 메뉴 id

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

}