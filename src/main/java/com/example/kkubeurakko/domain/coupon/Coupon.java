package com.example.kkubeurakko.domain.coupon;


import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.store.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id", updatable = false)
    private Long id;

    @NotNull
    @Column(name = "name", length = 100)
    private String name;

    @NotNull
    @Column(name = "discount_amount", precision = 10, scale = 2)
    private BigDecimal discountAmount;

    @NotNull
    @Column(name = "valid_from")
    private LocalDateTime validFrom;

    @NotNull
    @Column(name = "valid_until")
    private LocalDateTime validUntil;

    @NotNull
    @Column(name = "code", unique = true, length = 20)
    private String code;

    @NotNull
    @Column(name = "content",length = 300)
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "coupon_type", length = 20)
    private CouponType couponType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "condition_type", length = 20)
    private CouponConditionType conditionType; //쿠폰 사용 조건

    @Column(name = "min_order_amount", nullable = true, precision = 10, scale = 0)
    private BigDecimal minOrderAmount;

    @Column(name = "required_menu_items", nullable = true, length = 255)
    private String requiredMenuItems; //필수 메뉴 항목

    @NotNull
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

}