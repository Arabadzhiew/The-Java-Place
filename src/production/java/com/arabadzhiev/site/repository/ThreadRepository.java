package com.arabadzhiev.site.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.arabadzhiev.site.entity.Thread;

public interface ThreadRepository extends CrudRepository<Thread, Long>{
	
	public List<Thread> findBySubUrl(String url);
}
