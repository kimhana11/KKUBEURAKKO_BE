package com.example.kkubeurakko.domain.order;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.order.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuestOrder extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roadName;
    private String detailedAddress;
    private String postalCode;
    private String guestContact;
    private String guestPassword;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}