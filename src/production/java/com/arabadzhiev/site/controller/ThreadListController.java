package com.arabadzhiev.site.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.arabadzhiev.site.service.SubService;
import com.arabadzhiev.site.service.ThreadService;

@Controller
@RequestMapping("/sub/{subUrl}")
public class ThreadListController {
	
	@Autowired private ThreadService threadService;
	@Autowired private SubService subService;
	
	@RequestMapping
	public ModelAndView listThreads(Map<String, Object> model, @PathVariable("subUrl") String subUrl) {
		model.put("threads", threadService.getThreadsForSub(subUrl));
		model.put("subUrl", subUrl);
		model.put("subName", subService.getSub(subUrl).getName());
		
		return new ModelAndView("thread/list");
	}
	
	@RequestMapping(value = "thread/edit")
	public ModelAndView editThread(Map<String, Object> model, @Param("id") long id) {
		model.put("thread", threadService.getThread(id));
		
		return new ModelAndView();
	}
	
	@RequestMapping(value = "thread/delete", method = RequestMethod.POST)
	public RedirectView deleteThread(@Param("id") long id, @PathVariable("subUrl") String subUrl) {
		threadService.deleteThread(id);
		return new RedirectView("/sub/"+subUrl+"?deleted="+id, true);
	}

}
