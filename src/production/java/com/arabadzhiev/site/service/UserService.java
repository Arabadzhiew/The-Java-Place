package com.arabadzhiev.site.service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.arabadzhiev.site.entity.User;

public interface UserService extends UserDetailsService{
	
	@Override
	public User loadUserByUsername(String username);
	public void persistUser(@NotNull(message = "The user entity cannot be null.")User user,
							@NotBlank(message = "The password field cannot be blank")String password);

}
