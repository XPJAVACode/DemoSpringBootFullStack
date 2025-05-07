package com.BacthXP.Simple.Shared;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utills {
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "dfweifowqfhpifhwieohfweoifhewiocnoiwehfiowchaskcnlksjdcbjiwe";
	public String generateUserId(int length) {
		return generateRandomString(length); //option 2
	}
	
	private String generateRandomString(int length) {
		StringBuilder returnValue = new StringBuilder();
		for(int i=0;i<length;i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return returnValue.toString();
	}
}
