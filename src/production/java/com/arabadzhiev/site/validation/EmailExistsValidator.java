package com.arabadzhiev.site.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.arabadzhiev.site.repository.UserRepository;

public class EmailExistsValidator implements ConstraintValidator<EmailExists, String>{

	@Autowired private UserRepository userRepository;
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return userRepository.existsByEmail(email);
	}

}
