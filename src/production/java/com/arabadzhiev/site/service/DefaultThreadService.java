package com.arabadzhiev.site.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arabadzhiev.site.entity.Thread;
import com.arabadzhiev.site.repository.ThreadRepository;

@Service
@Transactional
public class DefaultThreadService implements ThreadService{
	
	@Autowired ThreadRepository threadRepository;
	
	@Override
	public void persistThread(Thread thread) {
		threadRepository.save(thread);
	}
	
	@Override
	public Thread getThread(long id) {
		
		return threadRepository.findById(id).get();
	}
	
	@Override
	public List<Thread> getAll() {
		List<Thread> threads = new ArrayList<Thread>();
		for(Thread thread : threadRepository.findAll()) {
			threads.add(thread);
		}
		
		return threads;
	}

}
