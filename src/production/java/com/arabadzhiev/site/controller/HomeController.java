package com.arabadzhiev.site.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arabadzhiev.site.service.ThreadService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired ThreadService threadService;
	
	@RequestMapping
	public String listThreads(Map<String, Object> model) {
		model.put("threads", threadService.getAll());
		
		return "thread/list";
	}
	
	
}
