package com.example.kkubeurakko.global.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.kkubeurakko.global.config.KakaoProperties;
import com.example.kkubeurakko.global.dto.KakaoTokenResponseDto;

import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoService {
	private final KakaoProperties kakaoProperties;

	private final String KAUTH_TOKEN_URL_HOST = "https://kauth.kakao.com";

	public String getAccessTokenFromKakao(String code) {
		log.info("Kakao Client ID: {}", kakaoProperties.getClientId());

		KakaoTokenResponseDto kakaoTokenResponseDto = WebClient.create(KAUTH_TOKEN_URL_HOST)
			.post()
			.uri(uriBuilder -> uriBuilder
				.path("/oauth/token")
				.queryParam("grant_type", "authorization_code")
				.queryParam("client_id", kakaoProperties.getClientId())
				.queryParam("client_secret", kakaoProperties.getClientSecret())
				.queryParam("redirect_uri", kakaoProperties.getRedirectUri())
				.queryParam("code", code)
				.build())
			.header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
			.retrieve()
			.bodyToMono(KakaoTokenResponseDto.class)
			.block();

		log.info(" [Kakao Service] Access Token ------> {}", kakaoTokenResponseDto.getAccessToken());
		log.info(" [Kakao Service] Refresh Token ------> {}", kakaoTokenResponseDto.getRefreshToken());
		log.info(" [Kakao Service] Id Token ------> {}", kakaoTokenResponseDto.getIdToken());
		log.info(" [Kakao Service] Scope ------> {}", kakaoTokenResponseDto.getScope());

		return kakaoTokenResponseDto.getAccessToken();
	}
}
