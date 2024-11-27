package com.example.kkubeurakko.global.oauth.service;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.kkubeurakko.domain.user.repository.UserRepository;
import com.example.kkubeurakko.global.oauth.config.KakaoProperties;
import com.example.kkubeurakko.global.dto.KaKaoUserInfoDto;
import com.example.kkubeurakko.global.dto.KakaoTokenResponseDto;

import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KakaoOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
	private final DefaultOAuth2UserService defaultOAuth2UserService = new DefaultOAuth2UserService();

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) {
		OAuth2User oAuth2User = defaultOAuth2UserService.loadUser(userRequest);

		// 카카오 사용자 정보를 가공하거나 추가 로직을 구현
		String id = oAuth2User.getAttribute("id").toString();
		Map<String, Object> kakaoAccount = (Map<String, Object>) oAuth2User.getAttributes().get("kakao_account");
		String email = (String) kakaoAccount.get("email");
		log.info(id);
		log.info(email);

		return new DefaultOAuth2User(oAuth2User.getAuthorities(), oAuth2User.getAttributes(), "id");
	}
}
