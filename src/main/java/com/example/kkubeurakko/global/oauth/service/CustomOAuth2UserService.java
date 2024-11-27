package com.example.kkubeurakko.global.oauth.service;

import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.kkubeurakko.global.oauth.dto.CustomOAuth2User;
import com.example.kkubeurakko.global.oauth.dto.KakaoResponse;
import com.example.kkubeurakko.global.oauth.dto.OAuth2Response;
import com.example.kkubeurakko.global.oauth.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

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
		else {
			return null;
		}
		String userNumber = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
		String nickname = oAuth2Response.getNickname();
		String email = oAuth2Response.getEmail();
		UserDto userDto = new UserDto("ROLE_USER", nickname, email, userNumber);

		return new CustomOAuth2User(userDto);
	}
}
