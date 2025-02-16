package com.example.kkubeurakko.domain.menu;

import com.example.kkubeurakko.domain.menu.menuenum.MenuLabel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Menu_MenuLabel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@NotNull
	@Column(name = "menu_id", nullable = false)
	private Long menuId;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "menu_label", nullable = false)
	private MenuLabel menuLabel;

	public Menu_MenuLabel(Long menuId, MenuLabel menuLabel){
		this.menuId = menuId;
		this.menuLabel = menuLabel;
	}
}
