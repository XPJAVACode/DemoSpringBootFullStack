package com.BacthXP.Simple.ExceptionHandler;

import java.util.Date;

public class ErrorMessage {

	Date errorOccuredDate;
	String message;
	
	public ErrorMessage(Date errorOccuredDate, String message) {
		super();
		this.errorOccuredDate = errorOccuredDate;
		this.message = message;
	}
	public Date getErrorOccuredDate() {
		return errorOccuredDate;
	}
	public void setErrorOccuredDate(Date errorOccuredDate) {
		this.errorOccuredDate = errorOccuredDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
