package com.example.kkubeurakko.global.api.service;

import com.example.kkubeurakko.global.jwt.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReissueService {

	private final JwtUtil jwtUtil;

	// 리프레시 토큰 추출
	public String extractRefreshTokenFromCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;										//예외를 던지도록 업데이트 예정
		}

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("Authorization")) {
				return cookie.getValue();
			}
		}
		return null;											//예외를 던지도록 업데이트 예정
	}

	// 리프레시 토큰 검증 및 액세스 토큰 새로 발급
	public String processTokenReissue(String refreshToken) {
		// 토큰 검증
		try {
			jwtUtil.isExpired(refreshToken);
		} catch (ExpiredJwtException e) {
			throw new IllegalArgumentException("refresh token expired");
		}

		String category = jwtUtil.getCategory(refreshToken);
		if (!"refresh".equals(category)) {
			throw new IllegalArgumentException("invalid refresh token");
		}

		// 유저 정보 추출
		String userNumber = jwtUtil.getUserNumber(refreshToken);
		String role = jwtUtil.getRole(refreshToken);

		// 새로운 액세스 토큰 생성
		return jwtUtil.createJwt("access", userNumber, role, 60*60*60L);
	}
}
