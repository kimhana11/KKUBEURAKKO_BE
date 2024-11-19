package com.example.kkubeurakko.global.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrations;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

import lombok.RequiredArgsConstructor;

@Configuration
public class KakaoOAuthConfig {
	private final KakaoProperties kakaoProperties;
	@Autowired
	public KakaoOAuthConfig(KakaoProperties kakaoProperties) {
		this.kakaoProperties = kakaoProperties;
	}
	@Bean
	public ClientRegistration kakaoClientRegistration() {
		return ClientRegistrations
			.fromIssuerLocation("https://kauth.kakao.com")
			.registrationId("kakao")
			.clientId(kakaoProperties.getClientId())
			.clientSecret(kakaoProperties.getClientSecret())
			.redirectUri(kakaoProperties.getRedirectUri())
			.scope("account_email")
			.authorizationUri("https://kauth.kakao.com/oauth/authorize")
			.tokenUri("https://kauth.kakao.com/oauth/token")
			.userInfoUri("https://kapi.kakao.com/v2/user/me")
			.userNameAttributeName("id")
			.clientName("Kakao")
			.build();
	}

	@Bean
	public InMemoryClientRegistrationRepository clientRegistrationRepository() {
		return new InMemoryClientRegistrationRepository(kakaoClientRegistration());
	}
}
