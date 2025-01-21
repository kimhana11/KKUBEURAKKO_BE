package com.example.kkubeurakko.global.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {
	private final SecretKey secretKey;
	public JwtUtil(@Value("${spring.jwt.secret}") String secret){
		secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
	}
	public String getUserNumber(String token){
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("userNumber", String.class);
	}
	public String getUserPhone(String token){
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("phone", String.class);
	}
	public String getRole(String token) {

		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
	}

	public Boolean isExpired(String token) {

		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
	}

	public String getCategory(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category", String.class);
	}
	public String createJwt(String category, String userNumber, String role, Long expiredMs){
		return Jwts.builder()
			.claim("category", category)
			.claim("userNumber", userNumber)
			.claim("role", role)
			.issuedAt(new Date(System.currentTimeMillis()))
			.expiration(new Date(System.currentTimeMillis() + expiredMs))
			.signWith(secretKey)
			.compact();
	}

	public String createJwtForGuest(String category, String phone, String role, Long expiredMs){
		return Jwts.builder()
			.claim("category", category)
			.claim("phone", phone)
			.claim("role", role)
			.issuedAt(new Date(System.currentTimeMillis()))
			.expiration(new Date(System.currentTimeMillis() + expiredMs))
			.signWith(secretKey)
			.compact();
	}
}
