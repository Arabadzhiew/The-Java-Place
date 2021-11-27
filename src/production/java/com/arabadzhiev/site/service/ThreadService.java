package com.arabadzhiev.site.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;

import com.arabadzhiev.site.entity.Thread;

public interface ThreadService {
	
	
	public void createThread(@P("thread")Thread thread);
	@PreAuthorize("#thread.user.username == authentication.name or hasAuthority('ADMIN')")
	public void updateThread(Thread thread);
	public Thread getThread(long id);
	public List<Thread> getThreadsForSub(String subUrl);
	public List<Thread> getAll();
	@PreAuthorize("#thread.user.username == authentication.name or hasAuthority(\"ADMIN\")")
	public void deleteThread(@P("thread")Thread thread);
}
