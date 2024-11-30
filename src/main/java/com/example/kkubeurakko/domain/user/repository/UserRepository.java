package com.example.kkubeurakko.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kkubeurakko.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserNumber(String userNumber);
}
