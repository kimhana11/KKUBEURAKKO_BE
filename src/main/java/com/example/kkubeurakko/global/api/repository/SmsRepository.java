package com.example.kkubeurakko.global.api.repository;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class SmsRepository {
	private final String PREFIX = "sms:";
	private final StringRedisTemplate stringRedisTemplate;

	// sms 인증 정보 생성 ㅔ서드
	public void createSmsCertification(String phone, String code){
		int LIMIT_TIME = 3 * 60; // 인증 코드의 유효시간 3분
		stringRedisTemplate.opsForValue()
			.set(PREFIX + phone, code, Duration.ofSeconds(LIMIT_TIME)); //redis에 키와 값을 설정, 유효 시간도 함께 설정
	}

	// sms 인증 정보 조회 메서드
	public String getSmsCertification(String phone){
		return stringRedisTemplate.opsForValue().get(PREFIX + phone);	//redis에 해당하는 키 값을 가져옴
	}

	// sms 인증 정보 삭제 메서드
	public void deleteSmsCertification(String phone){
		stringRedisTemplate.delete(PREFIX + phone);	 	// redis에서 해당 키 삭제
	}
	// 받은 키가 존재하는지 확인하는 메서드
	public boolean hasKey(String phone){
		return Boolean.TRUE.equals(stringRedisTemplate.hasKey(PREFIX + phone)); // redis의 키 존재 여부 확인
	}
}
