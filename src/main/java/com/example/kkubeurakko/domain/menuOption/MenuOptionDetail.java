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

    @Column(name = "option_value", nullable = false)
    private String optionValue;
    @Column(name = "additional_price", nullable = false)
    private BigDecimal additionalPrice;

    @ManyToOne
    @JoinColumn(name = "menu_option_id")
    private MenuOption menuOption;

}
