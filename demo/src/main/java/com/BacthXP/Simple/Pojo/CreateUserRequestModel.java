package com.BacthXP.Simple.Pojo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateUserRequestModel {

	@NotNull(message="First Name can not be null")
	@Size(min=2, message="first name can not be less than two characters")
	private String firstName;
	
	@NotNull(message="Last Name can not be null")
	@Size(min=2, message="last name can not be less than two characters")
	private String lastName;
	
	@NotNull(message="password can not be null")
	@Size(min=8, max=16,  message="password is not in recommended size")
	private String password;
	
	@NotNull(message="email can not be null")
	@Email
	private String email;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
