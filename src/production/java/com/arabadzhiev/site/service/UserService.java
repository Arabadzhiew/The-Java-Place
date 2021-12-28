package com.arabadzhiev.site.service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.arabadzhiev.site.entity.User;

public interface UserService extends UserDetailsService{
	
	@Override
	User loadUserByUsername(String username);
	void persistUser(@NotNull(message = "User entity passed to persist is null")User user,
		@NotNull(message = "Null password has been passed on to pu service method")
		@NotBlank(message = "Blank password has been passed on to pu service method")
		@Size(min = 10, message = "Illegal size password has been passed on to pu service method")
		String password);
	@PreAuthorize("#user.username == authentication.name or hasAuthority('ADMIN')")
	void updateUser(@NotNull(message = "User entity passed to update is null") @P("user") User user);
	boolean recoveryKeyValid(@NotNull(message = "Null recovery key was passed on initial check") 
		String recoveryKey);
	void changePassword(@NotNull(message = "Null rk was passed on secondary check") String recoveryKey, 
		@NotNull(message = "Null password has been passed on to cp service method")
		@NotBlank(message = "Blank password has been passed on to cp service method")
		@Size(min = 10, message = "Illegal size password has been passed on to cp service method")
		String password);
	void generateRecoveryKey(@NotNull(message = "Null email value was passed to generate rk")String email);
	long getCount();
	boolean exists(@NotNull(message = "Ajax check passed in a null username")String username);

}
