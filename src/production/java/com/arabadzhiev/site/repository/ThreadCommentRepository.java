package com.arabadzhiev.site.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.arabadzhiev.site.entity.ThreadComment;
import com.arabadzhiev.site.entity.User;
import com.arabadzhiev.site.entity.Thread;

public interface ThreadCommentRepository extends PagingAndSortingRepository<ThreadComment, Long>{
	Page<ThreadComment> findByThread(Thread thread, Pageable pageable);
	Page<ThreadComment> findByUser(User user, Pageable pageable);
}
