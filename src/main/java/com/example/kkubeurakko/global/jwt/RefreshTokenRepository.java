package com.example.kkubeurakko.global.jwt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	Boolean existsByRefresh(String refresh);		// 해당 refresh토큰이 존재하는지
	@Transactional
	void deleteByRefresh(String refresh);
}
