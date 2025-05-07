package com.BacthXP.Simple.Controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.BacthXP.Simple.Pojo.CreateUserRequestModel;
import com.BacthXP.Simple.Pojo.CreateUserResponseModel;
import com.BacthXP.Simple.Pojo.UserDto;
import com.BacthXP.Simple.Pojo.UserResponseModel;
import com.BacthXP.Simple.Service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	//registration process
	@PostMapping(value= "/createUser", 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
	)
	@ResponseBody
	public ResponseEntity<CreateUserResponseModel> createUser(@Validated @RequestBody CreateUserRequestModel createUserRequestModel) {
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userDto = mapper.map(createUserRequestModel, UserDto.class);
		UserDto createdUser = userService.cteateUser(userDto);
		
		CreateUserResponseModel response = mapper.map(createdUser, CreateUserResponseModel.class);
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	//secured endpoints
	@GetMapping(value="getAll/{userId}", produces= {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId){
		UserDto userDetails = userService.getUserByUserId(userId);
		UserResponseModel response = new ModelMapper().map(userDetails, UserResponseModel.class);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
		
	}
}
