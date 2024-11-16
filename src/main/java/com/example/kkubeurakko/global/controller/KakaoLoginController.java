package com.example.kkubeurakko.global.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kkubeurakko.global.service.KakaoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class KakaoLoginController {
	private final KakaoService kakaoService;
	@GetMapping("/callback")
	public ResponseEntity<?> callback(@RequestParam("code") String code) {
		String accessToken = kakaoService.getAccessTokenFromKakao(code);
		if(kakaoService.saveUserInfo(accessToken)){
			throw new RuntimeException();
		};
		return new ResponseEntity<>(HttpStatus.OK);
	}
}