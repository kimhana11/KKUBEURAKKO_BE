package com.example.kkubeurakko.global.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {

	@Bean
	public WebClient webClient() {
		HttpClient httpClient = HttpClient.create()
			.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000) // 연결 타임아웃 설정 (1초)
			.doOnConnected(connection -> connection
				.addHandlerLast(new ReadTimeoutHandler(10))  // 읽기 타임아웃 설정 (10초)
				.addHandlerLast(new WriteTimeoutHandler(10))) // 쓰기 타임아웃 설정 (10초)
			.responseTimeout(Duration.ofSeconds(1)); // 전체 응답 타임아웃 설정 (1초)

		return WebClient.builder()
			.clientConnector(new ReactorClientHttpConnector(httpClient))
			.build();
	}
}