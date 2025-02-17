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
    @Column(name = "store_id", updatable = false)
    private Long id;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "phone", length = 11)
    private String phone;

    @NotNull
    @Column(name = "open_time")
    private Time openTime;

    @NotNull
    @Column(name = "close_time")
    private Time closeTime;

    @NotNull
    @Column(name = "is_open", columnDefinition = "boolean default false")
    private Boolean isOpen;

    @NotNull
    @Column(name = "min_order_price", precision = 10, scale = 0)
    private BigDecimal minOrderPrice;

    @NotNull
    @Column(name = "delivery_radius")
    private Integer deliveryRadius;

    @NotNull
    @Column(name = "delivery_tip", precision = 10, scale = 0)
    private BigDecimal deliveryTip;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Menu> menuList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coupon> coupons = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alarm> alarms = new ArrayList<>();
}
