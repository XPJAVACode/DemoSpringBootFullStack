package com.BacthXP.Simple.ServiceImpl;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.BacthXP.Simple.Entity.UserEntity;
import com.BacthXP.Simple.Pojo.UserDto;
import com.BacthXP.Simple.Repository.UserRepository;
import com.BacthXP.Simple.Service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto cteateUser(UserDto userDto) {
		
		userDto.setUserId(UUID.randomUUID().toString()); //option 1
		userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserEntity entity = mapper.map(userDto, UserEntity.class);
		
		userRepository.save(entity);
		
		UserDto returnValue = mapper.map(entity, UserDto.class);
		
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity entity = userRepository.findByEmail(email);
		if(entity == null) throw new UsernameNotFoundException("Email ID not found");
		
		return new User(entity.getEmail(), entity.getEncryptedPassword()
				,true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserDto getuserDetailsByEmail(String email) {
		UserEntity entity =  userRepository.findByEmail(email);
		if(entity == null) throw new UsernameNotFoundException("Email ID not found");
		return new ModelMapper().map(entity, UserDto.class);
	}

	@Override
	public UserDto getUserByUserId(String userId){
		UserEntity entity = userRepository.findByUserId(userId);
		return new ModelMapper().map(entity, UserDto.class);
	}

	
}
