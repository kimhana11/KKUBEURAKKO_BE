package com.example.kkubeurakko.global.oauth.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.kkubeurakko.api.user.UserNotFoundException;
import com.example.kkubeurakko.domain.user.User;
import com.example.kkubeurakko.domain.user.UserRole;
import com.example.kkubeurakko.domain.user.UserRepository;
import com.example.kkubeurakko.global.oauth.dto.CustomOAuth2User;
import com.example.kkubeurakko.global.oauth.dto.KakaoResponse;
import com.example.kkubeurakko.global.oauth.dto.OAuth2Response;
import com.example.kkubeurakko.global.oauth.dto.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	private final UserRepository userRepository;
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		OAuth2User oAuth2User = super.loadUser(userRequest);

		log.info("oAuth2User:{}",oAuth2User);

		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		OAuth2Response oAuth2Response = null;
		if (registrationId.equals("kakao")) {
			oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
		}
		// else if (registrationId.equals("naver")) {
		//
		// 	oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
		// }
		// else if (registrationId.equals("google")) {
		//
		// 	oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
		// }
		String userNumber = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
		String email = oAuth2Response.getEmail();
		String nickname = oAuth2Response.getNickname();
		try{
			User existUser = userRepository.findByUserNumber(userNumber)
				.orElseThrow(()-> new UserNotFoundException());

			existUser = existUser.builder()
				.nickname(nickname)			//닉네임 변경 가능성 염두
				.build();
			userRepository.save(existUser);

			UserDto userDto = new UserDto(
				existUser.getRole(),
				nickname,
				existUser.getEmail(),
				existUser.getUserNumber()
			);

			return new CustomOAuth2User(userDto);
		} catch (UserNotFoundException e){
			User user = User.builder()
				.userNumber(userNumber)
				.email(email)
				.nickname(nickname)
				.role(UserRole.CUSTOMER)
				.build();

			userRepository.save(user);
			UserDto userDto = new UserDto(UserRole.CUSTOMER, nickname, email, userNumber);

			return new CustomOAuth2User(userDto);
		}
	}
}
