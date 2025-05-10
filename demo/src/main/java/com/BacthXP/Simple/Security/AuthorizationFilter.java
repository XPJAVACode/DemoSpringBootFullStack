package com.BacthXP.Simple.Security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.crypto.SecretKey;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.BacthXP.Simple.Entity.UserEntity;
import com.BacthXP.Simple.Repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthorizationFilter extends BasicAuthenticationFilter{

	UserRepository userRepository;
	
	public AuthorizationFilter(AuthenticationManager authenticationManager,
			UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, 
			FilterChain chain)
			throws IOException, ServletException {
			
	  String authorizationHeader =  req.getHeader(SecurityConstants.HEADER_STRING);
	  if(authorizationHeader == null || 
			  !authorizationHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
		  chain.doFilter(req, res);
		  return;
	  }
	  authorizationHeader = authorizationHeader.replace(SecurityConstants.TOKEN_PREFIX, "").trim();
	  UsernamePasswordAuthenticationToken authorization =  getAuthentication(authorizationHeader);
	  SecurityContextHolder.getContext().setAuthentication(authorization); 
	  chain.doFilter(req, res);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		String commonKey =SecurityConstants.getTokenSecret();
		if(commonKey == null) return null;
		
		SecretKey key =  Keys.hmacShaKeyFor(commonKey.getBytes());
		
		Claims claims =  Jwts.parser()
		.verifyWith(key)
		.build()
		.parseSignedClaims(token)
		.getPayload();
		
		String userID = claims.getSubject();
		if(userID != null) {
			UserEntity usereEntity =  userRepository.findByUserId(userID);
			UserPrincipal userPrincipal = new UserPrincipal(usereEntity);
			return new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
		}
		
		return null;
	}

}
