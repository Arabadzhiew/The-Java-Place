package com.arabadzhiev.site.service;

import javax.validation.constraints.NotNull;

import com.arabadzhiev.site.entity.ThreadComment;

public interface ThreadCommentService {
	
	public void persistComment(@NotNull(message = "Internal error: null comment") ThreadComment comment);

}
