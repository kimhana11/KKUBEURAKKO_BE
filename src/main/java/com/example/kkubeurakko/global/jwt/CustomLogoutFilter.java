package com.example.kkubeurakko.global.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CustomLogoutFilter extends GenericFilterBean {

	private final JwtUtil jwtUtil;
	private final RefreshTokenRepository refreshTokenRepository;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
		} else {
			chain.doFilter(request, response);
		}
	}

	private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		// 1. 요청 검증
		if (!isLogoutRequest(request)) {
			chain.doFilter(request, response);
			return;
		}

		try {
			// 2. Refresh 토큰 추출
			String refreshToken = extractRefreshToken(request)
				.orElseThrow(() -> new IllegalArgumentException("Refresh token is missing"));

			// 3. Refresh 토큰 검증
			validateRefreshToken(refreshToken);

			// 4. 로그아웃 처리
			performLogout(refreshToken, response);

			// 5. 성공 응답 반환
			response.setStatus(HttpServletResponse.SC_OK);
			log.info("Logout successful for refresh token: {}", refreshToken);
		} catch (IllegalArgumentException | ExpiredJwtException e) {
			log.error("Logout failed: {}", e.getMessage());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} catch (Exception e) {
			log.error("Unexpected error during logout", e);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private boolean isLogoutRequest(HttpServletRequest request) {
		return "/logout".equals(request.getRequestURI()) && "POST".equalsIgnoreCase(request.getMethod());
	}

	private Optional<String> extractRefreshToken(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return Optional.empty();
		}
		return Arrays.stream(cookies)
			.filter(cookie -> "Authorization".equals(cookie.getName()))
			.map(Cookie::getValue)
			.findFirst();
	}

	private void validateRefreshToken(String refreshToken) {
		jwtUtil.isExpired(refreshToken); // 만료 여부 확인
		if (!"refresh".equals(jwtUtil.getCategory(refreshToken))) {
			throw new IllegalArgumentException("Invalid refresh token category");
		}
		if (!refreshTokenRepository.existsByRefresh(refreshToken)) {
			throw new IllegalArgumentException("Refresh token does not exist in the database");
		}
	}

	private void performLogout(String refreshToken, HttpServletResponse response) {
		// DB에서 Refresh 토큰 삭제
		refreshTokenRepository.deleteByRefresh(refreshToken);

		// 브라우저 쿠키에서 Refresh 토큰 제거
		Cookie cookie = new Cookie("Authorization", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
