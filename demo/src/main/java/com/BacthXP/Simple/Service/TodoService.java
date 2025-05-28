package com.BacthXP.Simple.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.BacthXP.Simple.Entity.TodoEntity;
import com.BacthXP.Simple.Pojo.Todo;


public interface TodoService {

	public List<TodoEntity> getAllTodos(Long userId);
	public int addTodo(Todo todo);
	public int deleteTodo(Todo todo);
	public int updateTodo(Todo todo);
}
