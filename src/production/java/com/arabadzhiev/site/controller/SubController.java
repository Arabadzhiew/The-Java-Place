package com.arabadzhiev.site.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.arabadzhiev.site.service.SubService;
import com.arabadzhiev.site.service.ThreadService;

@Controller
@RequestMapping("/")
public class SubController {
	
	@Autowired SubService subService;
	@Autowired private ThreadService threadService;
	
	@RequestMapping
	public ModelAndView listSubs(Map<String, Object> model) {
		model.put("subs", subService.getSubs());
		
		return new ModelAndView("sub/list");
	}
	
	@RequestMapping("sub/{subUrl}")
	public ModelAndView listThreads(Map<String, Object> model, @PathVariable("subUrl") String subUrl) {
		model.put("threads", threadService.getThreadsForSub(subUrl));
		model.put("subUrl", subUrl);
		model.put("subName", subService.getSub(subUrl).getName());
		
		return new ModelAndView("thread/list");
	}

}
