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
    private String name;  //가게명
    private String address; //가게 위치
    private String phone; //가게 번호
    private Time open_time;  //오픈 시간
    private Time close_time; //마감시간
    private Boolean is_open; //현재 영업 상태
    private int minimum_order_price; //최소주문금액
    private Integer delivery_radius; //배달 가능 반경 km
    private int deliveryTip; //기본 배달비
    private double rating; //별점

}
