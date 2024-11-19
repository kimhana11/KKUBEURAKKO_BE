package com.example.kkubeurakko.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.kkubeurakko.global.oauth.handler.CustomOAuth2AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {
	private CustomOAuth2AuthenticationSuccessHandler customOAuth2AuthenticationSuccessHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/oauth2/**", "/login/**", "/logout").permitAll() // 인증 필요 없는 경로
				.anyRequest().authenticated() // 나머지 요청은 인증 필요
			)
			.oauth2Login(oauth2 -> oauth2
				.defaultSuccessUrl("http://localhost:3000/") // 인증 성공 후 리디렉션 URL
				.failureUrl("/login?error=true") // 인증 실패 시 리디렉션 URL
			)
			.logout(logout -> logout
				.logoutSuccessUrl("/") // 로그아웃 성공 시 리디렉션 URL
			);

		return http.build();
	}
}
