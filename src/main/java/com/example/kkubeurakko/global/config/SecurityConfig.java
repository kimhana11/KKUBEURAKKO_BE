package com.example.kkubeurakko.global.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.example.kkubeurakko.global.jwt.CustomLogoutFilter;
import com.example.kkubeurakko.global.jwt.JwtFilter;
import com.example.kkubeurakko.global.jwt.JwtUtil;
import com.example.kkubeurakko.global.jwt.RefreshTokenRepository;
import com.example.kkubeurakko.global.oauth.handler.CustomSuccessHandler;
import com.example.kkubeurakko.global.oauth.service.CustomOAuth2UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final CustomOAuth2UserService customOAuth2UserService;
	private final CustomSuccessHandler customSuccessHandler;
	private final JwtUtil jwtUtil;
	private final RefreshTokenRepository refreshTokenRepository;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf((auth) -> auth.disable());
		http
			.formLogin((auth) -> auth.disable());
		http
			.httpBasic((auth) -> auth.disable());
		http
			.addFilterBefore(
				new JwtFilter(jwtUtil),
				UsernamePasswordAuthenticationFilter.class
			);
		http
			.addFilterBefore(
				new CustomLogoutFilter(jwtUtil, refreshTokenRepository),
				LogoutFilter.class
			);
		//JWTFilter 추가
		http
			.addFilterAfter(new JwtFilter(jwtUtil), OAuth2LoginAuthenticationFilter.class);
		http
			.oauth2Login(
				((oauth2) -> oauth2
					.userInfoEndpoint((userInfoEndpointConfig) ->
						userInfoEndpointConfig.userService(customOAuth2UserService))
					.successHandler(customSuccessHandler)
				)
			);
		http
			.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/", "/reissue", "/access","/ws/**","/ws").permitAll()
				.anyRequest().authenticated()
			);
		http
			.sessionManagement((session) -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}
}