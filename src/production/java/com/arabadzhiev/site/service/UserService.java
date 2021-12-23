package com.arabadzhiev.site.service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.arabadzhiev.site.entity.User;

public interface UserService extends UserDetailsService{
	
	@Override
	User loadUserByUsername(String username);
	void persistUser(@NotNull(message = "User entity passed to persist is null")User user,
							@NotBlank(message = "The password field cannot be blank")String password);
	@PreAuthorize("#user.username == authentication.name or hasAuthority('ADMIN')")
	void updateUser(@NotNull(message = "User entity passed to update is null") @P("user") User user);
	long getCount();
	boolean exists(@NotNull(message = "Ajax check passed in a null username")String username);

}
