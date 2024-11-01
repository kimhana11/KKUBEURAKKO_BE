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

    private String name; // 쿠폰 이름
    private BigDecimal discountAmount; // 할인 금액 (또는 비율)
    private BigDecimal minOrderAmount; // 최소 주문 금액
    private LocalDateTime validFrom; // 쿠폰 유효 시작일
    private LocalDateTime validUntil; // 쿠폰 유효 종료일

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

}