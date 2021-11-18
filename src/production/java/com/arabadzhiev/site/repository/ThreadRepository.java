package com.arabadzhiev.site.repository;

import org.springframework.data.repository.CrudRepository;

import com.arabadzhiev.site.entity.Thread;

public interface ThreadRepository extends CrudRepository<Thread, Long>{
	
}
