package com.example.kkubeurakko.api.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kkubeurakko.api.service.user.UserService;
import com.example.kkubeurakko.global.oauth.dto.CustomOAuth2User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;
	@GetMapping("/phone-exists")
	public ResponseEntity<Boolean> checkExistsUserPhone(
		@AuthenticationPrincipal CustomOAuth2User customOAuth2User

	) {
		boolean existsUserPhone = userService.checkExistsUserPhone(customOAuth2User);
		return ResponseEntity.ok(existsUserPhone);
	}
}
