package com.arabadzhiev.site.entity;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
public class User implements UserDetails, CredentialsContainer{
	private static final long serialVersionUID = 1L;

	private long id;
	private String username;
	private byte[] hashedPassword;
	private String email;
	private String role;
	private LocalDateTime dateCreated;
	private Set<UserAuthority> authorities = new HashSet<>();
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "Hashed_Password")
	public byte[] getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(byte[] hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Column(name = "Date_Created", insertable = false, updatable = false)
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	//SECURITY
	@Override
	public void eraseCredentials() {
		this.hashedPassword = null;
		
	}
	@Override
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "User_Authority", joinColumns= {
			@JoinColumn(name = "User_Id")
	})
	public Set<UserAuthority> getAuthorities() {
		return this.authorities;
	}
	public void setAuthorities(Set<UserAuthority> authorities) {
		this.authorities = authorities;
	}
	@Override
	@Transient
	public String getPassword() {
		return this.getHashedPassword() == null ? null : 
			new String(this.getHashedPassword(), StandardCharsets.UTF_8);
	}
	@Override
	@Column(name = "Account_Non_Expired", insertable = false)
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	@Override
	@Column(name = "Account_Non_Locked", insertable = false)
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	@Override
	@Column(name = "Credentials_Non_Expired", insertable = false)
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	@Override
	@Column(name = "Enabled", insertable = false)
	public boolean isEnabled() {
		return this.enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof User && this.id == ((User)other).getId() ? true : false;
	}
	
	@Override
	public int hashCode(){
		return this.username.hashCode();
	}
	
	@Override
	public String toString() {
		return this.username;
	}
	

}
