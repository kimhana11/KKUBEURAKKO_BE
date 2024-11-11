package com.example.kkubeurakko.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.kkubeurakko.global.dto.KakaoTokenResponseDto;

import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
@ConfigurationProperties(prefix = "kakao")
public class KakaoService {
	private String clientId;

	private final String KAUTH_TOKEN_URL_HOST = "https://kauth.kakao.com";
	private final String KAUTH_USER_URL_HOST = "https://kapi.kakao.com";
	public String getAccessTokenFromKakao(String code) {
		log.info(clientId);
		KakaoTokenResponseDto kakaoTokenResponseDto = WebClient.create(KAUTH_TOKEN_URL_HOST).post()
			.uri(uriBuilder -> uriBuilder
				.scheme("https")
				.path("/oauth/token")
				.queryParam("grant_type", "authorization_code")
				.queryParam("client_id", "b73d1824d0eaff0315ea6abc6c256d91")
				.queryParam("code", code)
				.build(true))
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
