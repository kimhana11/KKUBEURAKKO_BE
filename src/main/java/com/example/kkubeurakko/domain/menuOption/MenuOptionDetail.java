package com.example.kkubeurakko.domain.menuOption;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @Column(name = "id", updatable = false)
    private Long id;

    @NotNull
    @Column(name = "option_value", length = 20)
    private String optionValue;

    @NotNull
    @Column(name = "additional_price", precision = 10)
    private BigDecimal additionalPrice;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "menu_option_id")
    private MenuOption menuOption;

}
