package com.example.kkubeurakko.domain.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.kkubeurakko.domain.menu.Menu_MenuLabel;
import com.example.kkubeurakko.domain.menu.menuenum.MenuLabel;

public interface Menu_MenuLabelRepository extends JpaRepository<Menu_MenuLabel, Long> {
	@Query("select m.menuId from Menu_MenuLabel m where m.menuLabel = :label")
	List<Long> findMenuIdListByLabel(@Param("label") MenuLabel label);
}
