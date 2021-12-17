package com.arabadzhiev.site.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arabadzhiev.site.entity.Sub;
import com.arabadzhiev.site.entity.Thread;
import com.arabadzhiev.site.entity.User;
import com.arabadzhiev.site.repository.SubRepository;
import com.arabadzhiev.site.repository.ThreadRepository;
import com.arabadzhiev.site.repository.UserRepository;

@Service
@Transactional
public class DefaultThreadService implements ThreadService{
	
	@Autowired ThreadRepository threadRepository;
	@Autowired SubRepository subRepository;
	@Autowired UserRepository userRepository;
	
	@Override
	public void createThread(Thread thread) {
		User user = userRepository.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		user.setLastActive(LocalDateTime.now());
		userRepository.save(user);
		
		thread.setUser(user);
		threadRepository.save(thread);
		
		Sub sub = subRepository.findByUrl(thread.getSub().getUrl());
		sub.setTotalThreads(sub.getTotalThreads() + 1);
		sub.setLastActiveThread(thread);
		subRepository.save(sub);
	}
	
	@Override
	public void updateThread(Thread thread) {
		User user = thread.getUser();
		user.setLastActive(LocalDateTime.now());
		userRepository.save(user);
		
		threadRepository.save(thread);
	}
	
	@Override
	public Thread getThread(long id) {
		
		return threadRepository.findById(id).get();
	}
	
	@Override
	public List<Thread> getThreadsForSub(String subUrl){
		List<Thread> threads = new ArrayList<>();
		for(Thread t : threadRepository.findBySubUrl(subUrl)) {
			threads.add(t);
		}
		
		return threads;
	}
	
	@Override
	public List<Thread> getAll() {
		List<Thread> threads = new ArrayList<Thread>();
		for(Thread thread : threadRepository.findAll()) {
			threads.add(thread);
		}
		
		return threads;
	}
	
	@Override
	public Page<Thread> getByUser(User user, Pageable pageable){
		return threadRepository.findByUser(user, pageable);
	}
	
	@Override
	public void deleteThread(Thread thread) {
		
		Sub sub = thread.getSub();
		Thread lastActive = sub.getLastActiveThread();
		if(thread.equals(lastActive)) {
			sub.setLastActiveThread(sub.getThreads().get(sub.getThreads().size()-2));
		}
		sub.removeThread(thread);
		sub.setTotalThreads(sub.getTotalThreads() - 1);
		sub.setTotalComments(sub.getTotalComments() - thread.getCommentCount());
		
		subRepository.save(sub);
		threadRepository.delete(thread);
		
	}
	
	@Override
	public Page<Thread> searchThread(String query, Pageable pageable){
		return threadRepository.search(query, pageable);
	}

}
