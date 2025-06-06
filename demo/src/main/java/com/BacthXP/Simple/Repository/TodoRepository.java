package com.BacthXP.Simple.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.BacthXP.Simple.Entity.TodoEntity;
import com.BacthXP.Simple.Pojo.TodoResponse;

public interface TodoRepository extends CrudRepository<TodoEntity, Long>{
//	@Query(value="SELECT * FROM todos WHERE user_Id = :userId", nativeQuery = true)
//	List<TodoEntity> findTodosByUserId(@Param("userId") String userId);
	
//	 List<TodoEntity> findByUserUserId(String userId); with userEntity which we dont want
	
	@Query("SELECT new com.BacthXP.Simple.Pojo.TodoResponse(t.id, t.name, t.isCompleted, t.description, t.endTime) "
			+ "FROM TodoEntity t WHERE t.user.userId = :userId")
	List<TodoResponse> getTodosByUserId(@Param("userId") String userId);
	
	@Query("SELECT new com.BacthXP.Simple.Pojo.TodoResponse(t.id, t.name, t.isCompleted, t.description, t.endTime) "
			+ "FROM TodoEntity t WHERE t.id = :id")
	List<TodoResponse> getTodoByTodoId(@Param("id") long todoId);
	
	//we could have used, findByUserEntity or findByUserId, these are inbuilt but @Query is another way to write
	//actual native SQL queries 
}
