package com.example.kkubeurakko.domain.order;


import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.order.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuestOrder extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_order_id", updatable = false)
    private Long id;

    @NotNull
    @Column(name = "road_name",nullable = false)
    private String roadName;

    @NotNull
    @Column(name = "detailed_address",nullable = false)
    private String detailedAddress;

    @NotNull
    @Column(name = "postal_code", nullable = false,length = 20)
    private String postalCode;

    @NotNull
    @Column(name = "guest_contact", nullable = false, length = 20)
    private String guestContact;

    @NotNull
    @Column(name = "guest_password", nullable = false)
    private String guestPassword;

    @NotNull
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}