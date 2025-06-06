package com.BacthXP.Simple.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.BacthXP.Simple.Entity.TodoEntity;
import com.BacthXP.Simple.Pojo.Todo;
import com.BacthXP.Simple.Pojo.TodoResponse;


public interface TodoService {

	public List<TodoResponse> getAllTodos(String userId);
	public List<TodoResponse> getATodo(Long todoId);
	public int addTodo(Todo todo);
	public int deleteTodo(long todoId);
	public int updateTodo(Todo todo);
}
