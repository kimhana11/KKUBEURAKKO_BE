package com.example.kkubeurakko.domain.user;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.address.Address;
import com.example.kkubeurakko.domain.cart.Cart;
import com.example.kkubeurakko.domain.coupon.UserCoupon;
import com.example.kkubeurakko.domain.order.Order;
import com.example.kkubeurakko.domain.review.Review;
import com.example.kkubeurakko.domain.user.embedded.UserIdentifier;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Embedded
    private UserIdentifier userIdentifier;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
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
        this.userIdentifier = new UserIdentifier(userNumber, email, nickname);
        this.role = role;
    }

    public void updateNickname(String nickname) {
        this.userIdentifier.updateNickname(nickname);
    }

    public void updateUserPhone(String phone){this.userIdentifier.updateUserPhone(phone);}
}