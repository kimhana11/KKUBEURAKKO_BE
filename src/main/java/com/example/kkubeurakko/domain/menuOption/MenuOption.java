package com.example.kkubeurakko.domain.menuOption;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.menu.Menu;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;  // 옵션 제목(ex 맛선택, 사리추가...)
    private boolean mult_select; //옵션 다중 선택 가능 여부

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @OneToMany(mappedBy = "menuOption", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuOptionDetail> details = new ArrayList<>();
}
