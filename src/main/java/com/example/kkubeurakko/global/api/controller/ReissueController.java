package com.example.kkubeurakko.global.api.controller;

import com.example.kkubeurakko.global.api.service.ReissueService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReissueController {

	private final ReissueService reissueService;

	// 리프레시 토큰으로 엑세스 토큰 재발급 api
	// 예외처리 수정 예정
	@PostMapping("/reissue")
	public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
		// 쿠키에서 리프레시 토큰 추출
		String refreshToken = reissueService.extractRefreshTokenFromCookies(request);

		if (refreshToken == null) {
			return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
		}

		try {
			// 새로운 액세스 토큰 생성
			String newAccessToken = reissueService.processTokenReissue(refreshToken);

			// 응답 헤더에 액세스 토큰 설정
			response.setHeader("Authorization", newAccessToken);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
