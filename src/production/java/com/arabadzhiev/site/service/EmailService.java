package com.arabadzhiev.site.service;


public interface EmailService {
	
	void sendRecoveryEmail(String email, String recoveryKey);
}
