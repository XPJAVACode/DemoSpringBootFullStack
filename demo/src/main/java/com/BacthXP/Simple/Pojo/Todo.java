package com.BacthXP.Simple.Pojo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class Todo {

	private int id;
	private String name;
	private String description;
	private Date startTime;
	private Date endTime;
	public Todo(int id, String name, String description, Date startTime, Date endTime) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Override
	public int hashCode() {
		return Objects.hash(description, endTime, id, name, startTime);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		return Objects.equals(description, other.description) && Objects.equals(endTime, other.endTime)
				&& id == other.id && Objects.equals(name, other.name) && Objects.equals(startTime, other.startTime);
	}
	@Override
	public String toString() {
		return "Todo [id=" + id + ", name=" + name + ", description=" + description + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}
}
