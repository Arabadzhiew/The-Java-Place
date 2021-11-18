package com.arabadzhiev.site.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.arabadzhiev.site.entity.Thread;
import com.arabadzhiev.site.service.SubService;
import com.arabadzhiev.site.service.ThreadService;

@Controller
@RequestMapping("sub/{subUrl}/thread/create")
public class ThreadPostController {
	
	@Autowired ThreadService threadService;
	@Autowired SubService subService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String createThread(Map<String, Object> model, @PathVariable("subUrl") String subUrl) {
		
		model.put("subUrl", subUrl);
		model.put("subName", subService.getSub(subUrl).getName());
		model.put("threadForm", new ThreadForm());
		
		return "thread/create";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView createThread(Map<String, Object> model, @PathVariable("subUrl") String subUrl, 
			@Valid ThreadForm threadForm,  Errors errors) {
		
		model.put("subUrl", subUrl);
		model.put("subName", subService.getSub(subUrl).getName());
		
		if(errors.hasErrors()) {
			return new ModelAndView("thread/create");
		}
		Thread thread = new Thread();
		thread.setTitle(threadForm.getTitle());
		thread.setBody(threadForm.getBody());
		thread.setSubUrl(subUrl);
		
		threadService.persistThread(thread);
		
		return new ModelAndView(new RedirectView("/sub/"+subUrl+"/thread/view?id=" + thread.getId(), true));
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
