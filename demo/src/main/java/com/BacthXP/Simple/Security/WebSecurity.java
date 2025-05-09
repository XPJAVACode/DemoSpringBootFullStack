package com.BacthXP.Simple.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.BacthXP.Simple.Service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurity{

	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = 
				http.getSharedObject(AuthenticationManagerBuilder.class);
		
		authenticationManagerBuilder.userDetailsService(userService)
		                            .passwordEncoder(bCryptPasswordEncoder);
		
		AuthenticationManager authenticationManager =  authenticationManagerBuilder.build();
		
//		AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager, userService, environment);
//		authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
		
	
		
		http.csrf((csrf)-> csrf.disable())
		.authorizeHttpRequests((authz)-> authz
		.requestMatchers(HttpMethod.POST, "/users/createUser")
		.permitAll()
		.requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
		.permitAll()
		.requestMatchers(HttpMethod.DELETE, "/users/**").hasAnyRole("ADMIN")
		.anyRequest().authenticated())
		.addFilter(new AuthenticationFilter(authenticationManager)) 
		.addFilter(new AuthorizationFilter(authenticationManager))
		.authenticationManager(authenticationManager)
		.sessionManagement((session)-> session
     		   .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.headers((headers)-> headers.frameOptions((frameOptions)-> frameOptions.sameOrigin()));
		
		return http.build();
		       
	}
}
