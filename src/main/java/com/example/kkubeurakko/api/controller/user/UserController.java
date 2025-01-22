package com.example.kkubeurakko.api.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.kkubeurakko.api.service.user.UserService;
import com.example.kkubeurakko.domain.user.UserRepository;
import com.example.kkubeurakko.global.common.CommonResponse;
import com.example.kkubeurakko.global.oauth.dto.CustomOAuth2User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import retrofit2.http.GET;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
	private final UserService userService;
	@GetMapping("/api/user/exists-phone")
	public ResponseEntity<Boolean> checkExistsUserPhone(
		@AuthenticationPrincipal CustomOAuth2User customOAuth2User

	) {
		boolean existsUserPhone = userService.checkExistsUserPhone(customOAuth2User);
		return ResponseEntity.status(HttpStatus.OK).body(existsUserPhone);
	}
}
