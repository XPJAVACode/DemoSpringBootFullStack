package com.BacthXP.Simple.Shared;

import org.springframework.stereotype.Component;

import com.BacthXP.Simple.Security.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Date;
import java.util.Random;

import javax.crypto.SecretKey;

@Component
public class Utills {
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "dfweifowqfhpifhwieohfweoifhewiocnoiwehfiowchaskcnlksjdcbjiwe";

	public String generateUserId(int length) {
		return generateRandomString(length); // option 2
	}

	private String generateRandomString(int length) {
		StringBuilder returnValue = new StringBuilder();
		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return returnValue.toString();
	}
	public String generateToken(String userId) {
		String commonKey = SecurityConstants.getTokenSecret();
		//SHA, HS-52 - encryption algorithms		
		SecretKey secretKey = Keys.hmacShaKeyFor(commonKey.getBytes());		
		return Jwts.builder()
				       .subject(userId)
				       .expiration(Date.from(Instant.now().plusMillis(Long.parseLong(SecurityConstants.getTokenExpirationTime()))))
				       .issuedAt(Date.from(Instant.now()))
				       .signWith(secretKey)
				       .compact(); //build the token
	}
	public static boolean hasTokenExpired(String token) {
		boolean returnValue = false;
		try {
			String commonKey = SecurityConstants.getTokenSecret();
			if (commonKey == null)
				return true;

			SecretKey key = Keys.hmacShaKeyFor(commonKey.getBytes());

			Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();

			Date tokenExpirationTime = claims.getExpiration();
			Date todayDate = new Date();
			returnValue = tokenExpirationTime.before(todayDate);
		} catch (ExpiredJwtException ext) {
			returnValue = true;
		}

		return returnValue;
	}
}
