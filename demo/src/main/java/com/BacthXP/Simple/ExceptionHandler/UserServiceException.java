package com.BacthXP.Simple.ExceptionHandler;

public class UserServiceException extends RuntimeException{

	private static final long serialVersionUID = -1750536184496748811L;
	
	public UserServiceException(String message) {
		super(message);
	}
}
