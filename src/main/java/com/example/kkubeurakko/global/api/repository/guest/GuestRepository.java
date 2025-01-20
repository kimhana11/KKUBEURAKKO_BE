package com.example.kkubeurakko.global.api.repository.guest;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.kkubeurakko.global.api.controller.reissue.request.GuestRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class GuestRepository {
	private final String PREFIX = "guest:";
	private final StringRedisTemplate stringRedisTemplate;

	public void createGuestInformation(GuestRequest guestRequest){
		int LIMIT_TIME = 10 * 60;	// 토큰의 유효시간과 같음
		String key = PREFIX + guestRequest.phoneNum();
		String value = String.format(
			"{\"postalCode\":\"%s\",\"roadName\":\"%s\",\"detailAddress\":\"%s\"}",
			guestRequest.postalCode(),
			guestRequest.roadName(),
			guestRequest.detailAddress()
		);
		stringRedisTemplate.opsForValue().set(key, value, Duration.ofSeconds(LIMIT_TIME));
	}

	public String getGuestInformation(String phone){
		return stringRedisTemplate.opsForValue().get(PREFIX + phone);
	}

	public void removeGuestInformation(String phone){
		String key = PREFIX + phone;
		stringRedisTemplate.delete(key);
	}

	public boolean hasKey(String phone){
		return Boolean.TRUE.equals(stringRedisTemplate.hasKey(PREFIX + phone)); // redis의 키 존재 여부 확인
	}
}
