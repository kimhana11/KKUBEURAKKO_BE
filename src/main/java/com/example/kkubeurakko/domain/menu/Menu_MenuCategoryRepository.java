package com.example.kkubeurakko.domain.menu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.kkubeurakko.domain.menu.Menu_MenuCategory;
import com.example.kkubeurakko.domain.menu.menuenum.MenuCategory;

@Repository
public interface Menu_MenuCategoryRepository extends JpaRepository<Menu_MenuCategory, Long> {
	@Query("select m.menuId from Menu_MenuCategory m where m.menuCategory = :category")
	List<Long> findMenuIdListByCategory(@Param("category") MenuCategory category);
}
