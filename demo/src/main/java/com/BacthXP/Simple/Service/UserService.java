package com.BacthXP.Simple.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.BacthXP.Simple.Pojo.UserDto;

public interface UserService extends UserDetailsService{

	public UserDto cteateUser(UserDto userDto);
	public UserDto getuserDetailsByEmail(String email);
	public UserDto getUserByUserId(String userId);
}
