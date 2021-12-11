package com.arabadzhiev.site.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.arabadzhiev.site.entity.ThreadComment;
import com.arabadzhiev.site.entity.Thread;

public interface ThreadCommentRepository extends CrudRepository<ThreadComment, Long>{
	Page<ThreadComment> findByThread(Thread thread, Pageable pageable);
}
