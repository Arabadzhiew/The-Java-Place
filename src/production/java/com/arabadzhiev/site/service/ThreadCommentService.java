package com.arabadzhiev.site.service;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;

import com.arabadzhiev.site.entity.ThreadComment;
import com.arabadzhiev.site.entity.User;
import com.arabadzhiev.site.entity.Thread;

public interface ThreadCommentService {
	
	@PreAuthorize("isAuthenticated()")
	void persistComment(@NotNull(message = "Internal error: null comment") ThreadComment comment);
	ThreadComment getComment(long id);
	Page<ThreadComment> getComments(Thread thread, Pageable pageable);
	Page<ThreadComment> getComments(User user, Pageable pageable);
	@PreAuthorize("#comment.user.username == authentication.name or hasAuthority('ADMIN')")
	void editComment(@P("comment")ThreadComment comment);
	@PreAuthorize("#comment.user.username == authentication.name or hasAuthority('ADMIN')")
	void deleteComment(@NotNull(message = "Internal error: null comment") 
		@P("comment")ThreadComment comment);

}
