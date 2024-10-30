package com.example.kkubeurakko.domain.menuOption;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.menu.Menu;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String optionName;  // 옵션 이름
    private BigDecimal additionalPrice;  // 옵션 추가 금액

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
}
