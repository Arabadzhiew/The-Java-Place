package com.arabadzhiev.site.service;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;

import com.arabadzhiev.site.entity.ThreadComment;
import com.arabadzhiev.site.entity.Thread;

public interface ThreadCommentService {
	
	public void persistComment(@NotNull(message = "Internal error: null comment") ThreadComment comment);
	public ThreadComment getComment(long id);
	public Page<ThreadComment> getComments(Thread thread, Pageable pageable);
	@PreAuthorize("#comment.user.username == authentication.name or hasAuthority('ADMIN')")
	public void editComment(@P("comment")ThreadComment comment);
	@PreAuthorize("#comment.user.username == authentication.name or hasAuthority('ADMIN')")
	public void deleteComment(@NotNull(message = "Internal error: null comment") 
		@P("comment")ThreadComment comment);

}
