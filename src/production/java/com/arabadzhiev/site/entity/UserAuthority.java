package com.arabadzhiev.site.entity;

import javax.persistence.Embeddable;

import org.springframework.security.core.GrantedAuthority;

@Embeddable
public class UserAuthority implements GrantedAuthority{
	private static final long serialVersionUID = 1L;
	
	public UserAuthority() {}
	
	public UserAuthority(String authority) {
		this.authority = authority;
	}
	
	private String authority;
	
	@Override
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
