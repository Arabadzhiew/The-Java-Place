package com.arabadzhiev.site.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.arabadzhiev.site.entity.Thread;
import com.arabadzhiev.site.service.ThreadService;

@Controller
@RequestMapping("thread/create")
public class ThreadPostController {
	
	@Autowired ThreadService threadService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String createThread(Map<String, Object> model) {
		
		model.put("threadForm", new ThreadForm());
		
		return "thread/create";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView createThread(Map<String, Object> model, 
			@Valid ThreadForm threadForm,  Errors errors) {
		if(errors.hasErrors()) {
			return new ModelAndView("thread/create");
		}
		Thread thread = new Thread();
		thread.setTitle(threadForm.getTitle());
		thread.setBody(threadForm.getBody());
		
		threadService.persistThread(thread);
		
		return new ModelAndView(new RedirectView("/thread/view?id=" + thread.getId(), true));
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
}
