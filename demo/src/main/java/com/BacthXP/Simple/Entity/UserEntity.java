package com.BacthXP.Simple.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false, length=100)
	private String userId;
	
	@Column(nullable = false, length=100)
	private String firstName;
	
	@Column(nullable = false, length=100)
	private String lastName;
	
	@Column(nullable = false, length=100)
	private String email;
	
	@Column(nullable = false, unique = true)
	private String encryptedPassword;
	
	@ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles",
				joinColumns=@JoinColumn(name="users_id", referencedColumnName = "id"),
				inverseJoinColumns=@JoinColumn(name="roles_id", referencedColumnName="id"))
	private Collection<RoleEntity> roles;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<TodoEntity> todos = new ArrayList<>();
	
	public List<TodoEntity> getTodos() {
		return todos;
	}

	public void setTodos(List<TodoEntity> todos) {
		this.todos = todos;
	}


	public Collection<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Collection<RoleEntity> roles) {
		this.roles = roles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
}
