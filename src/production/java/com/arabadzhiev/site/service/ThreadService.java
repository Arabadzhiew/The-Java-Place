package com.arabadzhiev.site.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;

import com.arabadzhiev.site.entity.Sub;
import com.arabadzhiev.site.entity.Thread;
import com.arabadzhiev.site.entity.User;

public interface ThreadService {
	
	@PreAuthorize("isAuthenticated()")
	void createThread(@P("thread")Thread thread);
	@PreAuthorize("#thread.user.username == authentication.name or hasAuthority('ADMIN')")
	void updateThread(@P("thread")Thread thread);
	Thread getThread(long id);
	List<Thread> getThreadsForSub(String subUrl);
	List<Thread> getAll();
	Page<Thread> getByUser(User user, Pageable pageable);
	Page<Thread> getBySub(Sub sub, Pageable pageable);
	@PreAuthorize("#thread.user.username == authentication.name or hasAuthority(\"ADMIN\")")
	void deleteThread(@P("thread")Thread thread);
	Page<Thread> searchThread(String query, Pageable pagealble);
}
