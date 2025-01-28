package com.example.kkubeurakko.domain.menu;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.kkubeurakko.api.service.menu.response.FindMenuResponse;

public interface MenuRepository extends JpaRepository<Menu, Long> {
	@Query("select m.price, m.id from Menu m where m.name = :name")
	FindMenuResponse findPriceByMenuNameAndId(@Param("name") String name);
}
