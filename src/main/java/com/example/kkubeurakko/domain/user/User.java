package com.example.kkubeurakko.domain.user;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.address.Address;
import com.example.kkubeurakko.domain.cart.Cart;
import com.example.kkubeurakko.domain.coupon.UserCoupon;
import com.example.kkubeurakko.domain.order.Order;
import com.example.kkubeurakko.domain.review.Review;
import com.example.kkubeurakko.domain.user.userenum.UserRole;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @Column(name = "user_id", updatable = false)
    private Long id;

    @NotNull
    @Column(name = "user_number", unique = true)
    private String userNumber; //사용자 정보 제공자 + 제공 id 숫자

    @NotNull
    @Column(name = "email")
    private String email;

    @Column(name = "phone", unique = true, length = 13)
    private String phone;

    @NotNull
    @Column(name = "nickname", unique = true)
    private String nickname;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
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

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }

    public void updateUserPhone(String phone){
        this.phone = phone;
    }
}