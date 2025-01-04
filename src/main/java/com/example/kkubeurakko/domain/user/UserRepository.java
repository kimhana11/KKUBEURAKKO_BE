package com.example.kkubeurakko.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kkubeurakko.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserNumber(String userNumber);
}
