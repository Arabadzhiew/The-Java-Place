package com.arabadzhiev.site.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arabadzhiev.site.entity.Sub;
import com.arabadzhiev.site.entity.Thread;
import com.arabadzhiev.site.entity.ThreadComment;
import com.arabadzhiev.site.repository.SubRepository;
import com.arabadzhiev.site.repository.ThreadCommentRepository;
import com.arabadzhiev.site.repository.ThreadRepository;


@Service
@Transactional
public class DefaultThreadCommentService implements ThreadCommentService{
	
	@Autowired ThreadCommentRepository commentRepository;
	@Autowired ThreadRepository threadRepository;
	@Autowired SubRepository subRepository;
	
	@Override
	public void persistComment(ThreadComment comment) {
		
		commentRepository.save(comment);
		
		Thread thread = comment.getThread();
		thread.setCommentCount(thread.getCommentCount() + 1);
		threadRepository.save(thread);
		
		
		Sub sub = subRepository.findByUrl(comment.getThread().getSub().getUrl());
		sub.setTotalComments(sub.getTotalComments() + 1);
		sub.setLastActiveThread(comment.getThread());
		
		subRepository.save(sub);
	}
	
	@Override
	public ThreadComment getComment(long id) {
		return commentRepository.findById(id).get();
	}
	
	@Override
	public void deleteComment(ThreadComment comment) {
		Thread thread = comment.getThread();
		Sub sub = thread.getSub();
		thread.setCommentCount(thread.getCommentCount() - 1);
		thread.getComments().remove(comment);
		sub.setTotalComments(sub.getTotalComments() - 1);
		
		subRepository.save(sub);
		threadRepository.save(thread);
		
		commentRepository.delete(comment);
		
	}

}
