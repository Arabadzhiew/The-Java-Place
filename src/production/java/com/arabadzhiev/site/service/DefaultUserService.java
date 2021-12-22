package com.arabadzhiev.site.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.arabadzhiev.site.entity.User;
import com.arabadzhiev.site.repository.UserRepository;

@Service
@Transactional	
public class DefaultUserService implements UserService{
	
	private static final int HASHING_ROUNDS = 10;
	
	@Autowired private UserRepository userRepository;

	@Override
	public User loadUserByUsername(String username) {
		
		User user = this.userRepository.getByUsername(username);
		//making sure authorities and password are loaded
		user.getAuthorities().size();
		user.getPassword();
		
		return user;
	}

	@Override
	public void persistUser(User user, String password) {
		user.setHashedPassword(BCrypt.hashpw(password, BCrypt.gensalt(HASHING_ROUNDS)).getBytes());
		this.userRepository.save(user);
		
	}
	@Override
	public void updateUser(User user) {
		user.setLastActive(LocalDateTime.now());
		userRepository.save(user);
	}
	
	@Override 
	public long getCount() {
		return this.userRepository.count();
	}

}
