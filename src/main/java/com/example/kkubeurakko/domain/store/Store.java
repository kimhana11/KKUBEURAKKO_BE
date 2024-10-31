package com.example.kkubeurakko.domain.store;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.menu.Menu;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Long id;

    private String name;  //가게명
    private String address; //가게 위치
    private String phone; //가게 번호
    private Time open_time;  //오픈 시간
    private Time close_time; //마감시간
    private Boolean is_open; //현재 영업 상태
    private BigDecimal minimum_order_price; //최소주문금액
    private Integer delivery_radius; //배달 가능 반경 km
    private BigDecimal deliveryTip; //기본 배달비
    private double rating; //별점

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Menu> menuList = new ArrayList<>();
}
