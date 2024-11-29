package com.example.kkubeurakko.domain.user.entity;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.address.Address;
import com.example.kkubeurakko.domain.cart.Cart;
import com.example.kkubeurakko.domain.coupon.UserCoupon;
import com.example.kkubeurakko.domain.order.Order;
import com.example.kkubeurakko.domain.review.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userNumber", unique = true, nullable = false)
    private String userNumber; //사용자 정보 제공자 + 제공 id 숫자

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "nickname", unique = true, nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserCoupon> userCoupons = new ArrayList<>();

    @Builder
    User(
        String userNumber,
        String email,
        String nickname,
        UserRole role
    ){
        this.userNumber = userNumber;
        this.email = email;
        this.nickname = nickname;
        this.role = role;
    }
    User(String nickname){
        this.nickname = nickname;
    }


}