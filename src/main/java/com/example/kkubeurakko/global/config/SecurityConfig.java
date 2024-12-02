package com.example.kkubeurakko.global.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.example.kkubeurakko.global.jwt.JwtFilter;
import com.example.kkubeurakko.global.jwt.JwtUtil;
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
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

				@Override
				public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

					CorsConfiguration configuration = new CorsConfiguration();

					configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
					configuration.setAllowedMethods(Collections.singletonList("*"));
					configuration.setAllowCredentials(true);
					configuration.setAllowedHeaders(Collections.singletonList("*"));
					configuration.setMaxAge(3600L);

					configuration.setExposedHeaders(Collections.singletonList("Set-Cookie"));
					configuration.setExposedHeaders(Collections.singletonList("Authorization"));

					return configuration;
				}
			}));
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
			.oauth2Login(
				((oauth2) -> oauth2
				.userInfoEndpoint((userInfoEndpointConfig) ->
					userInfoEndpointConfig.userService(customOAuth2UserService))
					.successHandler(customSuccessHandler)
				)
			);
		http
			.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/", "/reissue").permitAll()
				.anyRequest().authenticated()
			);
		http
			.sessionManagement((session) -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}
}
