package com.example.kkubeurakko.global.api.service;


import com.example.kkubeurakko.global.common.ResponseMsgEnum;
import com.example.kkubeurakko.global.exception.JwtException;
import com.example.kkubeurakko.global.jwt.JwtUtil;
import com.example.kkubeurakko.global.jwt.RefreshTokenRepository;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReissueService {

	private final JwtUtil jwtUtil;
	private final RefreshTokenRepository refreshTokenRepository;
	public String reissue(HttpServletRequest request) {
		// Refresh 토큰 가져오기
		String refresh = getRefreshTokenFromCookies(request);

		if (refresh == null) {
			throw new JwtException(ResponseMsgEnum.JWT_REFRESH_NULL);
		}
		// Refresh 토큰 유효성 검증
		validateRefreshToken(refresh);
		isExistRefreshToken(refresh);
		// 사용자 정보 추출
		String userNumber = jwtUtil.getUserNumber(refresh);
		String role = jwtUtil.getRole(refresh);
		// 새로운 토큰 생성
		String newAccess = jwtUtil.createJwt("access", userNumber, role, 60 * 60 * 60L);
		return newAccess;
	}

	public String getRefreshTokenFromCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			throw new JwtException(ResponseMsgEnum.COOKIE_NULL);
		}

		for (Cookie cookie : cookies) {
			if ("Authorization".equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		throw new JwtException(ResponseMsgEnum.JWT_REFRESH_NULL);
	}

	private void validateRefreshToken(String refresh) {
		try {
			jwtUtil.isExpired(refresh);
		} catch (ExpiredJwtException e) {
			throw new JwtException(ResponseMsgEnum.JWT_REFRESH_EXPIRED);		// 로그아웃 요청을 보내도록 -> 재로그인 요청하도록
		}

		String category = jwtUtil.getCategory(refresh);
		if (!"refresh".equals(category)) {
			throw new JwtException(ResponseMsgEnum.JWT_REFRESH_NULL); // 로그아웃 요청을 보내도록 -> 재로그인 요청하도록
		}
	}

	public void isExistRefreshToken(String refresh){
		Boolean isExist = refreshTokenRepository.existsByRefresh(refresh);
		if (!isExist) {
			throw new JwtException(ResponseMsgEnum.JWT_REFRESH_EXPIRED);  // 로그아웃 요청을 보내도록 -> 재로그인 요청하도록
		}
	}
}
