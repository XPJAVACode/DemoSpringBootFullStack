package com.BacthXP.Simple.Security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.BacthXP.Simple.Entity.AuthorityEntity;
import com.BacthXP.Simple.Entity.RoleEntity;
import com.BacthXP.Simple.Entity.UserEntity;

public class UserPrincipal implements UserDetails{

	private static final long serialVersionUID = -2774868660727716649L;
	
	private UserEntity userEntity;
	private String userId;

	public UserPrincipal(UserEntity userEntity) {
		super();
		this.userEntity = userEntity;
		this.userId = this.userEntity.getUserId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		Collection<GrantedAuthority> authorities = new HashSet<>();
		Collection<AuthorityEntity> authorityEntity = new HashSet<>();
		
		Collection<RoleEntity> roles= userEntity.getRoles();
		if(roles == null) return authorities;
		roles.forEach((role)->{
			authorities.add(new SimpleGrantedAuthority(role.getName()));
			authorityEntity.addAll(role.getAuthorities());
		});
		
		authorityEntity.forEach((authorityEntites)->{
			authorities.add(new SimpleGrantedAuthority(authorityEntites.getName()));
		});
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.userEntity.getEncryptedPassword();
	}

	@Override
	public String getUsername() {
		return this.userEntity.getEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId =  userId;
	}
}
