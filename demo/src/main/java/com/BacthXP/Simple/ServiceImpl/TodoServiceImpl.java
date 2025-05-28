package com.BacthXP.Simple.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BacthXP.Simple.Annotation.ExecutionTime;
import com.BacthXP.Simple.Entity.TodoEntity;
import com.BacthXP.Simple.Pojo.Todo;
import com.BacthXP.Simple.Repository.TodoInmemoryRepository;
import com.BacthXP.Simple.Repository.TodoRepository;
import com.BacthXP.Simple.Service.TodoService;

@Service
public class TodoServiceImpl implements TodoService{

	@Autowired
	TodoRepository todoRepository;
	
	public List<TodoEntity> getAllTodos(Long userId) {
		return todoRepository.findTodosByUserId(userId);
	}

	@Override
	public int addTodo(Todo todo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteTodo(Todo todo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateTodo(Todo todo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
