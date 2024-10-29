package com.example.kkubeurakko.domain.store;

import com.example.kkubeurakko.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Time open_time;
    private Time close_time;
    private Boolean is_open; //현재 영업 상태
    private Integer delivery_radius; //배달 가능 반경 km
    private int deliveryTip;
    private double rating;
    private String announcement;

}
