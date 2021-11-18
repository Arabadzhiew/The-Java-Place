package com.arabadzhiev.site.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.arabadzhiev.site.service.SubService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired SubService subService;
	
	@RequestMapping
	public ModelAndView listSubs(Map<String, Object> model) {
		model.put("subs", subService.getSubs());
		
		return new ModelAndView("sub/list");
	}
	
	
}
