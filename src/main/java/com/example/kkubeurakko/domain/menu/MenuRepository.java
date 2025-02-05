package com.example.kkubeurakko.domain.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.kkubeurakko.api.service.menu.response.FindMenuResponse;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}