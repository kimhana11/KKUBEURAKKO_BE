package com.example.kkubeurakko.domain.menu;

import com.example.kkubeurakko.domain.menu.menuenum.MenuLabel;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Menu_MenuLabel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long menuId;
	@Enumerated(EnumType.STRING)
	private MenuLabel menuLabel;

	public Menu_MenuLabel(Long menuId, MenuLabel menuLabel){
		this.menuId = menuId;
		this.menuLabel = menuLabel;
	}
}
