package com.arabadzhiev.site.repository;

import org.springframework.data.repository.CrudRepository;

import com.arabadzhiev.site.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	public User getByUsername(String username);
}
