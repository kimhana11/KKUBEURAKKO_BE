package com.example.kkubeurakko.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import com.example.kkubeurakko.global.oauth.service.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final CustomOAuth2UserService customOAuth2UserService;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf((auth) -> auth.disable());
		http
			.formLogin((auth) -> auth.disable());
		http
			.httpBasic((auth) -> auth.disable());
		http
			.oauth2Login(
				((oauth2) -> oauth2
				.userInfoEndpoint((userInfoEndpointConfig) ->
					userInfoEndpointConfig.userService(customOAuth2UserService))
				)
			);
		http
			.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/").permitAll()
				.anyRequest().authenticated()
			);
		http
			.sessionManagement((session) -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}
}
