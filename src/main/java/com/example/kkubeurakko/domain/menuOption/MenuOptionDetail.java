package com.example.kkubeurakko.domain.menuOption;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOptionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value; // 세부 옵션 내용
    private BigDecimal additionalPrice;  // 옵션 추가 금액

    @ManyToOne
    @JoinColumn(name = "menu_option_id")
    private MenuOption menuOption;
}
