package com.arabadzhiev.site.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.arabadzhiev.site.entity.Thread;
import com.arabadzhiev.site.entity.ThreadComment;
import com.arabadzhiev.site.service.SubService;
import com.arabadzhiev.site.service.ThreadService;

@Controller
@RequestMapping("sub/{subUrl}/thread/view")
public class ThreadViewController {
	
	@Autowired ThreadService threadService;
	@Autowired SubService subService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewThread(Map<String, Object> model, @Param("id") long id, 
			@PathVariable("subUrl") String subUrl) {
		model.put("subUrl", subUrl);
		model.put("subName", subService.getSub(subUrl).getName());
		
		Thread thread = threadService.getThread(id);
		model.put("thread", thread);
		model.put("comments", thread.getComments());
		model.put("commentForm", new CommentForm());
		
		return "thread/view";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView postComment(Map<String, Object> model, @Param("id") long id,
			@PathVariable("subUrl") String subUrl,
			@Valid CommentForm commentForm, Errors errors) {
		model.put("subUrl", subUrl);
		model.put("subName", subService.getSub(subUrl).getName());
		
		Thread thread = threadService.getThread(id);
		if(errors.hasErrors()) {
			model.put("thread", thread);
			model.put("comments", thread.getComments());
			return new ModelAndView("thread/view");
		}
		
		ThreadComment comment = new ThreadComment();
		comment.setThreadId(id);
		comment.setBody(commentForm.getBody());
		thread.addComment(comment);
		threadService.persistThread(thread);
		
		return new ModelAndView(new RedirectView("/sub/"+subUrl+"/thread/view?id=" + id + "&commented", true));
	}
	
	public static class CommentForm{
		@NotBlank(message = "The body can not be empty")
		private String body;

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}
	}
}
