package com.example.kkubeurakko.domain.menu;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu_MenuCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long menuId;
	@Enumerated(EnumType.STRING)
	private MenuCategory menuCategory;

	public Menu_MenuCategory(Long menuId, MenuCategory menuCategory){
		this.menuId = menuId;
		this.menuCategory = menuCategory;
	}
}
