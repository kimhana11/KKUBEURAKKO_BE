package com.example.kkubeurakko.domain.store;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.alarm.Alarm;
import com.example.kkubeurakko.domain.coupon.Coupon;
import com.example.kkubeurakko.domain.menu.Menu;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "address",nullable = false)
    private String address;

    @NotNull
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @NotNull
    @Column(name = "open_time", nullable = false)
    private Time openTime;

    @NotNull
    @Column(name = "close_time", nullable = false)
    private Time closeTime;

    @NotNull
    @Column(name = "is_open", nullable = false, columnDefinition = "boolean default false")
    private Boolean isOpen;

    @NotNull
    @Column(name = "min_order_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal minOrderPrice;

    @NotNull
    @Column(name = "delivery_radius", nullable = false)
    private Integer deliveryRadius;

    @NotNull
    @Column(name = "delivery_tip", nullable = false, precision = 10, scale = 2)
    private BigDecimal deliveryTip;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Menu> menuList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coupon> coupons = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alarm> alarms = new ArrayList<>();
}
