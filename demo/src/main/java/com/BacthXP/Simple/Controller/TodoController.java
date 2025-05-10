package com.BacthXP.Simple.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BacthXP.Simple.Pojo.Todo;
import com.BacthXP.Simple.Service.TodoService;

@RestController
@RequestMapping("/todo/management/")
public class TodoController {

	@Autowired
	TodoService todoService;
	
	@GetMapping("/getAll")
	public List<Todo> getTodosFromController(){
		return todoService.getAllTodosFromServiceImpl();
	}
	//getAllTodos(everyone), createTodo(request can be sent only by ADMIN and logged in user, response should be accessed only by admin), 
	//deleteTodo(only admin), updateTodo(logged in user and admin)
}
