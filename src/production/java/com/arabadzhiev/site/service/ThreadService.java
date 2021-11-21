package com.arabadzhiev.site.service;

import java.util.List;

import com.arabadzhiev.site.entity.Thread;

public interface ThreadService {
	
	
	public void persistThread(Thread thread);
	public Thread getThread(long id);
	public List<Thread> getThreadsForSub(String subUrl);
	public List<Thread> getAll();
	public void deleteThread(long id);
}
