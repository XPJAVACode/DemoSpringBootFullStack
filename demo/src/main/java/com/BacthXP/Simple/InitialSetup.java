package com.BacthXP.Simple;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.BacthXP.Simple.Entity.AuthorityEntity;
import com.BacthXP.Simple.Entity.RoleEntity;
import com.BacthXP.Simple.Entity.UserEntity;
import com.BacthXP.Simple.Repository.AuthorityRepository;
import com.BacthXP.Simple.Repository.RoleRepository;
import com.BacthXP.Simple.Repository.UserRepository;
import com.BacthXP.Simple.Shared.Roles;
import com.BacthXP.Simple.Shared.Utills;

import jakarta.transaction.Transactional;

@Component
public class InitialSetup {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
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
		RoleEntity roleAdmin = createRole(Roles.ROLE_ADMIN.name(), Arrays.asList(readAuthority, writeAuthority, deleteAuthority));
		
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
}
