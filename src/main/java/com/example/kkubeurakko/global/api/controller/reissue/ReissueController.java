package com.example.kkubeurakko.global.api.controller.reissue;

import com.example.kkubeurakko.global.api.service.ReissueService;
import com.example.kkubeurakko.global.common.CommonResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReissueController {

	private final ReissueService reissueService;

	// 리프레시 토큰으로 엑세스 토큰 재발급 api
	// 예외처리 수정 예정
	// 첫 accessToken 발급 api
	@GetMapping("/reissue")
	public ResponseEntity<?> reissue(HttpServletRequest request) {
		// Reissue service 호출
		String newAccessToken = reissueService.reissue(request);
		// 응답에 새로운 토큰 설정
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", newAccessToken);
		return ResponseEntity.ok()
			.headers(headers)
			.body(new CommonResponse(200, "토큰이 재발급 되었습니다", null));
	}
}
