package com.BacthXP.Simple.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BacthXP.Simple.Entity.TodoEntity;
import com.BacthXP.Simple.Entity.UserEntity;
import com.BacthXP.Simple.Pojo.Todo;
import com.BacthXP.Simple.Repository.UserRepository;
import com.BacthXP.Simple.Service.TodoService;
import com.BacthXP.Simple.Service.UserService;

@RestController
@RequestMapping("/todo/management/")
public class TodoController {

	@Autowired
	TodoService todoService;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/getAll/{name}")
	public List<TodoEntity> getTodosFromController(@RequestParam(required=true) String userId){
		UserEntity userEntity = userRepository.findByUserId(userId);
		List<TodoEntity> todoEntity = todoService.getAllTodos(userEntity.getId());
		return todoEntity; // please change it to TodoResposne class
	}
	//getAllTodos(everyone), createTodo(request can be sent only by ADMIN and logged in user, response should be accessed only by admin), 
	//deleteTodo(only admin), updateTodo(logged in user and admin)
}
