package com.example.kkubeurakko.domain.menu;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kkubeurakko.domain.menu.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}