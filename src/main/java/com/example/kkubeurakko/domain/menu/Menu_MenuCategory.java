package com.example.kkubeurakko.domain.menu;

import com.example.kkubeurakko.domain.menu.menuenum.MenuCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu_MenuCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menu_menu_category_id", updatable = false)
	private Long id;

	@NotNull
	@Column(name = "menu_id")
	private Long menuId;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "menu_category")
	private MenuCategory menuCategory;

	public Menu_MenuCategory(Long menuId, MenuCategory menuCategory){
		this.menuId = menuId;
		this.menuCategory = menuCategory;
	}
}
