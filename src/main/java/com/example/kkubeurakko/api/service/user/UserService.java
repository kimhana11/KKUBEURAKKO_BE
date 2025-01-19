package com.example.kkubeurakko.global.api.service.user;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kkubeurakko.api.exception.user.UserNotFoundException;
import com.example.kkubeurakko.domain.user.User;
import com.example.kkubeurakko.domain.user.UserRepository;
import com.example.kkubeurakko.global.oauth.dto.CustomOAuth2User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
	private final UserRepository userRepository;

	//전화번호 인증 후 해당 유저의 전화번호 저장
	@Transactional
	public void saveUserPhone(String phone, Authentication authentication){
		CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
		String userNumber = customUserDetails.getUserNumber();
		User user = userRepository.findByUserNumber(userNumber).orElseThrow(
			() -> new UserNotFoundException()
		);
		user.updateUserPhone(phone);
		log.info("전화번호 저장 완료");
	}
}
