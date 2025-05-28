package com.BacthXP.Simple;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.BacthXP.Simple.Entity.AuthorityEntity;
import com.BacthXP.Simple.Entity.RoleEntity;
import com.BacthXP.Simple.Entity.TodoEntity;
import com.BacthXP.Simple.Entity.UserEntity;
import com.BacthXP.Simple.Repository.AuthorityRepository;
import com.BacthXP.Simple.Repository.RoleRepository;
import com.BacthXP.Simple.Repository.TodoRepository;
import com.BacthXP.Simple.Repository.UserRepository;
import com.BacthXP.Simple.Shared.Roles;
import com.BacthXP.Simple.Shared.Utills;


@Component
public class InitialSetup {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	TodoRepository todoRepository;
	
	@Autowired
	Utills utills;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	@EventListener
	public void onApplicationEvent(ApplicationReadyEvent event) {
		System.out.println("From application ready event....");
		
		AuthorityEntity readAuthority = createAuthority("READ_AUTHORITY");
		AuthorityEntity writeAuthority = createAuthority("WRITE_AUTHORITY");
		AuthorityEntity deleteAuthority = createAuthority("DELETE_AUTHORITY");
		
		createRole(Roles.ROLE_USER.name(), Arrays.asList(readAuthority, writeAuthority));
		RoleEntity roleAdmin = createRole(Roles.ROLE_ADMIN.name(), 
				Arrays.asList(readAuthority, writeAuthority, deleteAuthority));
		
		if(roleAdmin == null) return;
		
		UserEntity adminUser = new UserEntity();
		adminUser.setFirstName("Suman");
		adminUser.setLastName("Chatterjee");
		adminUser.setEmail("admin@test.com");
		adminUser.setUserId(utills.generateUserId(30));
		adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("12345678"));
		adminUser.setRoles(Arrays.asList(roleAdmin));
		
		UserEntity alreadyStoredAdminUser  = userRepository.findByEmail("admin@test.com");
		if(alreadyStoredAdminUser == null) {
			userRepository.save(adminUser);
		}
		System.out.println("Adding todos");
		//Assuming admin user has many todos
		createTodos(adminUser, "Learn Java");
		createTodos(adminUser, "Learn Javscript");
		createTodos(adminUser, "Learn Angular");
	}
	
	@Transactional
	private AuthorityEntity createAuthority(String name) {
		AuthorityEntity entity =  authorityRepository.findByName(name);
		if(entity == null) {
			entity = new AuthorityEntity(name);
			authorityRepository.save(entity);
		}
		return entity;
	}
	
	@Transactional
	private RoleEntity createRole(String name, Collection<AuthorityEntity> authorities) {
		RoleEntity entity =  roleRepository.findByName(name);
		if(entity == null) {
			entity = new RoleEntity(name);
			entity.setAuthorities(authorities);
			roleRepository.save(entity);
		}
		return entity;
	}
	
	@Transactional
	private void createTodos(UserEntity adminUser, String name) {
		TodoEntity todo = new TodoEntity();
		todo.setName(name);
		todo.setIsCompleted("false");
		todo.setDescription("Learning is a key path to success");
		todo.setStartTime(Timestamp.valueOf(LocalDateTime.now()));
		todo.setEndTime(Timestamp.valueOf(LocalDateTime.now().plusDays(5)));
		todo.setUserEntity(adminUser);
		todoRepository.save(todo);
	}
}
