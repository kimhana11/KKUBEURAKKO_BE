package com.example.kkubeurakko.global.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kkubeurakko.global.api.service.ReissueService;
import com.example.kkubeurakko.global.jwt.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TokenController {
	private final ReissueService reissueService;
	private final JwtUtil jwtUtil;
	@GetMapping("/access")
	public ResponseEntity<?> provideAccessToken(
		HttpServletRequest request
	){
		String refresh = reissueService.getRefreshTokenFromCookies(request);
		if(refresh == null){
			throw new RuntimeException();
		}
		String access = jwtUtil.createJwt("access", jwtUtil.getUserNumber(refresh), jwtUtil.getUserNumber(refresh), 60*60L);
		log.info("여기 왔니?");
		return ResponseEntity.ok().header("Authorization", access).body("성공");
	}
}