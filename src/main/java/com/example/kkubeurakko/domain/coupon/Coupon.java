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
    @Column(name = "coupon_id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull
    @Column(name = "discount_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal discountAmount;

    @NotNull
    @Column(name = "valid_from", nullable = false)
    private LocalDateTime validFrom;

    @NotNull
    @Column(name = "valid_until", nullable = false)
    private LocalDateTime validUntil;

    @NotNull
    @Column(name = "code", nullable = false, unique = true, length = 20)
    private String code;

    @Column(name = "content", length = 255)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "coupon_type", nullable = false, length = 20)
    private CouponType couponType;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition_type", nullable = false, length = 20)
    private CouponConditionType conditionType;


    @Column(name = "min_order_amount", precision = 10, scale = 2)
    private BigDecimal minOrderAmount;

    @Column(name = "required_menu_items", length = 255)
    private String requiredMenuItems;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

}