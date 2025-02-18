package com.example.kkubeurakko.domain.menuOption;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.menu.Menu;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "label", length = 20)
    private String label;

    @NotNull
    @Column(name = "multi_select", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean multiSelect;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "menu_id", updatable = false)
    private Menu menu;

    @OneToMany(mappedBy = "menuOption", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuOptionDetail> details = new ArrayList<>();
}
