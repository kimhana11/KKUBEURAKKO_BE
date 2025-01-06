package com.example.kkubeurakko.global.config;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableRedisRepositories
public class RedisConfig {
	private final RedisProperties redisProperties;

	@Bean
	public RedisConnectionFactory redisConnectionFactory(){
		return new LettuceConnectionFactory(redisProperties.getHost(), redisProperties.getPort());
	}
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>(); // RedisTemplate 인스턴스 생성
		redisTemplate.setConnectionFactory(redisConnectionFactory()); // Redis 연결 팩토리 설정
		redisTemplate.setKeySerializer(new StringRedisSerializer()); // 키를 문자열로 직렬화하도록 설정
		redisTemplate.setValueSerializer(new StringRedisSerializer()); // 값을 문자열로 직렬화하도록 설정

		return redisTemplate; // 설정이 완료된 RedisTemplate 인스턴스를 반환
	}
}
