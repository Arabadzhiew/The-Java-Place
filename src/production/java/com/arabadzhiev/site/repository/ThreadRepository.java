package com.arabadzhiev.site.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.arabadzhiev.site.entity.Thread;
import com.arabadzhiev.site.entity.User;

public interface ThreadRepository extends CrudRepository<Thread, Long>, SearchableRepository<Thread>{
	
	List<Thread> findBySubUrl(String url);
	Page<Thread> findByUser(User user, Pageable pageable);
}
