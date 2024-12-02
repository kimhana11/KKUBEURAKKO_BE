package com.example.kkubeurakko.global.api.service;

import java.util.HashMap;
import java.util.Map;

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
	public Map<String, String> reissue(HttpServletRequest request) {
		// Refresh 토큰 가져오기
		String refresh = getRefreshTokenFromCookies(request);

		if (refresh == null) {
			throw new IllegalArgumentException("Refresh token null");
		}

		// Refresh 토큰 유효성 검증
		validateRefreshToken(refresh);

		// 사용자 정보 추출
		String userNumber = jwtUtil.getUserNumber(refresh);
		String role = jwtUtil.getRole(refresh);

		// 새로운 토큰 생성
		String newAccess = jwtUtil.createJwt("access", userNumber, role, 60*60*60L);
		String newRefresh = jwtUtil.createJwt("refresh", userNumber, role, 60*60*2400L);

		// 생성된 토큰 반환
		Map<String, String> tokens = new HashMap<>();
		tokens.put("access", newAccess);
		tokens.put("refresh", newRefresh);

		return tokens;
	}

	private String getRefreshTokenFromCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}

		for (Cookie cookie : cookies) {
			if ("refresh".equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return null;
	}

	private void validateRefreshToken(String refresh) {
		try {
			jwtUtil.isExpired(refresh);
		} catch (ExpiredJwtException e) {
			throw new IllegalArgumentException("Refresh token expired");
		}

		String category = jwtUtil.getCategory(refresh);
		if (!"refresh".equals(category)) {
			throw new IllegalArgumentException("Invalid refresh token");
		}
	}

	public Cookie createCookie(String name, String value) {
		Cookie cookie = new Cookie(name, value);
		// cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setMaxAge(24 * 60 * 60); // 7일
		return cookie;
	}
}

