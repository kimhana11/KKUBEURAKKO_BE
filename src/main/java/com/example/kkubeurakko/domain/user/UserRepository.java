package com.example.kkubeurakko.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kkubeurakko.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserNumber(String userNumber);
}
