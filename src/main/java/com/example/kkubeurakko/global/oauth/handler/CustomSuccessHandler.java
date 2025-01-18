package com.example.kkubeurakko.global.oauth.handler;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.kkubeurakko.api.exception.user.UserNotFoundException;
import com.example.kkubeurakko.domain.user.User;
import com.example.kkubeurakko.domain.user.UserRepository;
import com.example.kkubeurakko.global.api.service.ReissueService;
import com.example.kkubeurakko.global.jwt.JwtUtil;
import com.example.kkubeurakko.global.jwt.RefreshToken;
import com.example.kkubeurakko.global.jwt.RefreshTokenRepository;
import com.example.kkubeurakko.global.oauth.dto.CustomOAuth2User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private final JwtUtil jwtUtil;
	private final RefreshTokenRepository refreshTokenRepository;
	private final UserRepository userRepository;
	@Override
	public void onAuthenticationSuccess(
		HttpServletRequest request,
		HttpServletResponse response,
		Authentication authentication
	) throws
		IOException, ServletException {
		//OAuth2User
		CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
		String userNumber = customUserDetails.getUserNumber();

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
		GrantedAuthority auth = iterator.next();
		String role = auth.getAuthority();
		String refresh = jwtUtil.createJwt("refresh", userNumber, role, 60*60*2400L);
		saveRefreshToken(userNumber, refresh, 86400000L);

		response.addCookie(createCookie(refresh));
		response.sendRedirect("http://localhost:3000/?redirectedFromSocialLogin=true");
	}

	private Cookie createCookie(String value) {

		Cookie cookie = new Cookie("Authorization", value);
		cookie.setMaxAge(60*60*60);
		//cookie.setSecure(true);		https
		cookie.setPath("/");
		cookie.setHttpOnly(true);		//js가 쿠키 가져가지 못하도록

		return cookie;
	}

	private void saveRefreshToken(String userNumber, String refresh, Long expiredMs){
		Date date = new Date(System.currentTimeMillis() + expiredMs);
		RefreshToken refreshToken = RefreshToken.builder()
			.userNumber(userNumber)
			.refresh(refresh)
			.expiration(date.toString())
			.build();

		refreshTokenRepository.save(refreshToken);
	}

}
