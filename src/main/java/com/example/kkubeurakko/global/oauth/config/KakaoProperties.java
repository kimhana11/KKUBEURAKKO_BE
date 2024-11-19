package com.example.kkubeurakko.global.oauth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "kakao")
public class KakaoProperties {
	private String clientId;
	private String clientSecret;
	private String redirectUri;
}
