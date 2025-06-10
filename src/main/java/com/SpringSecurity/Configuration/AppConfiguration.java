package com.SpringSecurity.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.SpringSecurity.Service.custmerservice;

import lombok.SneakyThrows;

@Configuration
@EnableWebSecurity
public class AppConfiguration {
	
	@Autowired
	private custmerservice service;
	
	@Bean
	public BCryptPasswordEncoder pwdEncoder()
	{
		  return new BCryptPasswordEncoder();

	}
	
	public DaoAuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider	authprovider = new DaoAuthenticationProvider();
		
		  authprovider.setPasswordEncoder(pwdEncoder());
		  authprovider.setUserDetailsService(service);
		  
		  return authprovider; 
		
	}
	
	@Bean
	@SneakyThrows
	public AuthenticationManager authManager(AuthenticationConfiguration config)
	{
		
	      return config.getAuthenticationManager();
	}
	
	      
	
	@Bean
	@SneakyThrows
	public SecurityFilterChain security(HttpSecurity http)
	{
		
		http.authorizeHttpRequests(req->{
			req.requestMatchers("/findbyname")
			.permitAll()
			.anyRequest()
			.authenticated();

		  })
		.oauth2Login(oauth -> oauth
				   .defaultSuccessUrl("/greet"));
		    return http.csrf().disable().build();
	}
}
