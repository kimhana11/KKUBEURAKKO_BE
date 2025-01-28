package com.example.kkubeurakko.domain.menu;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuRepository extends JpaRepository<Menu, Long> {
	@Query("select m.price from Menu m where m.name = :name")
	BigDecimal findPriceByMenuName(@Param("name") String name);
}
