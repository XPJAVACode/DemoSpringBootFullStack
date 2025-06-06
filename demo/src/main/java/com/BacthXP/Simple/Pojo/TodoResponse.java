package com.BacthXP.Simple.Pojo;

import java.sql.Timestamp;

public class TodoResponse {

	private long id;
	private String name;
	private String isCompleted;
	private String description;
	private Timestamp endTime;
	public TodoResponse(long id, String name, String isCompleted, String description, Timestamp endTime) {
		super();
		this.id = id;
		this.name = name;
		this.isCompleted = isCompleted;
		this.description = description;
		this.endTime = endTime;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(String isCompleted) {
		this.isCompleted = isCompleted;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
}
