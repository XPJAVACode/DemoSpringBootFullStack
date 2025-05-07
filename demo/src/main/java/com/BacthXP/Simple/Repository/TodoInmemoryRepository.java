package com.BacthXP.Simple.Repository;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.BacthXP.Simple.Pojo.Todo;

@Repository
public class TodoInmemoryRepository {
 //inmemory logic
  private static final Map<Integer, Todo> map = new HashMap<>();
 
  static {
	  map.put(1, new Todo(1, "Core Java", "Need to learn", new Date(0), new Date(0)));
  }
  
  public List<Todo> getAllTodosFromRepository(){
	  //return new ArrayList<>(map.values());
	  List<Todo> result = map.values().stream().collect(Collectors.toList());
	  return result;
  }
  
  
}
