package com.example.kkubeurakko.global.jwt;

import java.io.IOException;

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.kkubeurakko.domain.user.UserRole;
import com.example.kkubeurakko.global.oauth.dto.CustomOAuth2User;
import com.example.kkubeurakko.global.oauth.dto.UserDto;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	private final JwtUtil jwtUtil;
	@Override
	protected void doFilterInternal(HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		String accessToken = request.getHeader("Authorization");

		// 토큰이 없는 경우 필터 체인을 계속 진행
		if (accessToken == null) {
			filterChain.doFilter(request, response);
			return;
		}

		try {
			// JWT 토큰 유효성 확인
			jwtUtil.isExpired(accessToken);

			String category = jwtUtil.getCategory(accessToken);
			if (!"access".equals(category)) {
				throw new IllegalArgumentException("잘못된 토큰 유형입니다.");
			}

			String userNumber = jwtUtil.getUserNumber(accessToken);
			String role = jwtUtil.getRole(accessToken);

			// 인증 객체 생성 및 SecurityContext에 저장
			UserRole userRole = UserRole.findRole(role);
			CustomOAuth2User customOAuth2User = new CustomOAuth2User(new UserDto(userNumber, userRole));
			Authentication authToken = new UsernamePasswordAuthenticationToken(
				customOAuth2User, null, customOAuth2User.getAuthorities()
			);
			SecurityContextHolder.getContext().setAuthentication(authToken);

		} catch (ExpiredJwtException e) {
			throw new InsufficientAuthenticationException("JWT가 만료되었습니다.", e);
		} catch (IllegalArgumentException e) {
			throw new InsufficientAuthenticationException("JWT 인증 실패: " + e.getMessage(), e);
		}

		filterChain.doFilter(request, response);
	}
}
