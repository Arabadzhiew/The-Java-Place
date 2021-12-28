package com.arabadzhiev.site.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.arabadzhiev.site.entity.User;
import com.arabadzhiev.site.repository.UserRepository;

@Service
@Transactional	
public class DefaultUserService implements UserService{
	
	private static final int HASHING_ROUNDS = 10;
	
	private Encoder encoder = Base64.getUrlEncoder().withoutPadding();
	
	@Autowired private UserRepository userRepository;
	@Autowired private EmailService emailService;
	
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
		if(user.getUsername().contains(" ")) {
			throw new IllegalArgumentException("An attempt to register a username with spaces was made.");
		}
		user.setRole("User");
		user.setLastActive(LocalDateTime.now());
		user.setHashedPassword(BCrypt.hashpw(password, BCrypt.gensalt(HASHING_ROUNDS)).getBytes());
		this.userRepository.save(user);
		
	}
	@Override
	public void updateUser(User user) {
		user.setLastActive(LocalDateTime.now());
		userRepository.save(user);
	}
	@Override
	public boolean recoveryKeyValid(String recoveryKey) {
		if(recoveryKey.length() != 69) return false;
		String uIdString;
		try {
			uIdString = recoveryKey.substring(67);
		}catch(StringIndexOutOfBoundsException e) {
			return false;
		}
		long userId;
		try {
			userId = Long.parseLong(uIdString);
		}catch(NumberFormatException e) {
			return false;
		}
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isEmpty()) return false;
		User user = optionalUser.get();
		if(user.getRecoveryKey() == null) return false;
		String userRK = encoder.encodeToString(user.getRecoveryKey()).concat(""+user.getId());
		
		if(recoveryKey.equals(userRK)) return true;
		return false;
	}
	@Override
	public void changePassword(String recoveryKey, String password) {
		if(!this.recoveryKeyValid(recoveryKey)) {
			throw new IllegalArgumentException();
		}
		
		long userId = Long.parseLong(recoveryKey.substring(67));
		User user = userRepository.findById(userId).get();
		user.setHashedPassword(BCrypt.hashpw(password, BCrypt.gensalt(HASHING_ROUNDS)).getBytes());
		user.setRecoveryKey(null);
		user.setRecoveryKeyTimestamp(null);
		userRepository.save(user);
	}
	@Override
	public void generateRecoveryKey(String email) {
		User user = userRepository.getByEmail(email);
		
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[50];
		random.nextBytes(bytes);
		user.setRecoveryKey(bytes);
		user.setRecoveryKeyTimestamp(LocalDateTime.now());
		userRepository.save(user);
		
		emailService.sendRecoveryEmail(email, encoder.encodeToString(bytes).concat(""+user.getId()));
	}
	
	@Override 
	public long getCount() {
		return this.userRepository.count();
	}

	@Override
	public boolean exists(@NotNull(message = "Ajax check passed in a null username") String username) {
		return userRepository.existsByUsername(username);
	}
	
	@Scheduled(fixedDelay = 1_800_000L, initialDelay = 1_800_000L)
	public void wipeRecoveryKeys() {
		LocalDateTime oneHourAgo = LocalDateTime.now().minus(1L, ChronoUnit.HOURS);
		List<User> users = new ArrayList<>();
		for(User u : userRepository.findAll()) {
			users.add(u);
		}
		
		users.removeIf(u -> u.getRecoveryKey() == null);
		
		for(User u : users) {
			if(u.getRecoveryKeyTimestamp() == null) {
				u.setRecoveryKey(null);
				userRepository.save(u);
			}else if(u.getRecoveryKeyTimestamp().isBefore(oneHourAgo)) {
				u.setRecoveryKey(null);
				u.setRecoveryKeyTimestamp(null);
				userRepository.save(u);
			}
		}
	}

}
