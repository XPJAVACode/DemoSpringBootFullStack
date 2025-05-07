package com.BacthXP.Simple.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.BacthXP.Simple.Pojo.Todo;


public interface TodoService {

	public List<Todo> getAllTodosFromServiceImpl();
	public int addTodo(Todo todo);
	public int deleteTodo(Todo todo);
	public int updateTodo(Todo todo);
}
