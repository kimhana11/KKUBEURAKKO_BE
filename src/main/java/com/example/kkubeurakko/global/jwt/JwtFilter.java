package com.example.kkubeurakko.global.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.kkubeurakko.domain.user.entity.UserRole;
import com.example.kkubeurakko.global.oauth.dto.CustomOAuth2User;
import com.example.kkubeurakko.global.oauth.dto.UserDto;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	private final JwtUtil jwtUtil;
	@Override
	protected void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain
	) throws ServletException, IOException {

		String authorization = null;
		Cookie[] cookies = request.getCookies();

		for (Cookie cookie : cookies) {
			log.info("cookie : {}", cookie.getName());
			if (cookie.getName().equals("Authorization")) {
				authorization = cookie.getValue();
			}
		}
		//Authorization 헤더 검증
		if (authorization == null) {
			System.out.println("token null");
			filterChain.doFilter(request, response);

			//조건이 해당되면 메소드 종료 (필수)
			return;
		}
		//토큰
		String token = authorization;

		//토큰 소멸 시간 검증
		if (jwtUtil.isExpired(token)) {

			log.info("token expired");
			filterChain.doFilter(request, response);

			//조건이 해당되면 메소드 종료 (필수)
			return;
		}
		//토큰에서 username과 role 획득
		String userNumber = jwtUtil.getUserNumber(token);
		UserRole role = UserRole.findRole(jwtUtil.getRole(token));

		//userDTO를 생성하여 값 set
		UserDto userDTO = new UserDto(
			userNumber,
			role
		);
		//UserDetails에 회원 정보 객체 담기
		CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDTO);

		//스프링 시큐리티 인증 토큰 생성
		Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
		//세션에 사용자 등록
		SecurityContextHolder.getContext().setAuthentication(authToken);

		filterChain.doFilter(request, response);
	}
}
