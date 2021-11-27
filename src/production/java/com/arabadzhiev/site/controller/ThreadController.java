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
import com.arabadzhiev.site.service.ThreadCommentService;
import com.arabadzhiev.site.service.ThreadService;

@Controller
@RequestMapping("sub/{subUrl}/thread")
public class ThreadController {
	
	@Autowired ThreadService threadService;
	@Autowired ThreadCommentService commentService;
	@Autowired SubService subService;
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createThread(Map<String, Object> model, @PathVariable("subUrl") String subUrl) {
		
		model.put("sub", subService.getSub(subUrl));
		model.put("threadForm", new ThreadForm());
		model.put("action", "create");
		
		return "thread/threadForm";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ModelAndView createThread(Map<String, Object> model, @PathVariable("subUrl") String subUrl, 
			@Valid ThreadForm threadForm,  Errors errors) {
		
		model.put("sub", subService.getSub(subUrl));
		
		if(errors.hasErrors()) {
			return new ModelAndView("thread/threadForm");
		}
		Thread thread = new Thread();
		thread.setTitle(threadForm.getTitle());
		thread.setBody(threadForm.getBody());
		thread.setSub(subService.getSub(subUrl));
		
		threadService.createThread(thread);
		
		return new ModelAndView(new RedirectView("/sub/"+subUrl+"/thread/view?id=" + thread.getId(), true));
	}
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public ModelAndView viewThread(Map<String, Object> model, @Param("id") long id, 
			@PathVariable("subUrl") String subUrl) {
		
		Thread thread = threadService.getThread(id);
		String threadSubUrl = thread.getSub().getUrl();
		model.put("sub", subService.getSub(threadSubUrl));
		model.put("thread", thread);
		model.put("comments", thread.getComments());
		model.put("commentForm", new CommentForm());
		
		if(!threadSubUrl.equals(subUrl)) {
			return new ModelAndView(new RedirectView("/sub/"+threadSubUrl+"/thread/view?id="+id, true));
		}
		
		return new ModelAndView("thread/view");
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView editThread(Map<String, Object> model, @Param("id") long id, 
			@PathVariable("subUrl") String subUrl) {
		
		ThreadForm threadForm = new ThreadForm();
		Thread thread = threadService.getThread(id);
		threadForm.setTitle(thread.getTitle());
		threadForm.setBody(thread.getBody());
		
		model.put("sub", subService.getSub(subUrl));
		model.put("threadForm", threadForm);
		model.put("action", "edit");
		
		return new ModelAndView("thread/threadForm");
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public ModelAndView editThread(ThreadForm threadForm, Errors errors, Map<String, Object> model,
			@Param("id") long id, @PathVariable("subUrl") String subUrl) {
		
		Thread thread = threadService.getThread(id);
		if(errors.hasErrors()) {
			threadForm.setTitle(thread.getTitle());
			threadForm.setBody(thread.getBody());
			model.put("threadForm", threadForm);
			model.put("sub", subService.getSub(subUrl));
			return new ModelAndView("thread/threadForm");
		}
		
		thread.setTitle(threadForm.getTitle());
		thread.setBody(threadForm.getBody());
		threadService.updateThread(thread);
		
		return new ModelAndView(new RedirectView("/sub/"+subUrl, true));
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public RedirectView deleteThread(@Param("id") long id, @PathVariable("subUrl") String subUrl) {
		Thread thread = threadService.getThread(id);
		threadService.deleteThread(thread);
		return new RedirectView("/sub/"+subUrl+"?deleted="+id, true);
	}
	
	@RequestMapping(value = "view", method = RequestMethod.POST)
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
		comment.setThread(thread);
		comment.setBody(commentForm.getBody());
		commentService.persistComment(comment);
		
		return new ModelAndView(new RedirectView("/sub/"+subUrl+"/thread/view?id=" + id + "&commented", true));
	}
	
	
	public static class ThreadForm{
		@NotBlank(message = "The title field can not be empty")
		private String title;
		@NotBlank(message = "The body can not be empty")
		private String body;
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getBody() {
			return body;
		}
		public void setBody(String body) {
			this.body = body;
		}
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