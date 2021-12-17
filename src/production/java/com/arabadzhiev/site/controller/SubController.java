package com.arabadzhiev.site.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.arabadzhiev.site.controller.SearchController.SearchForm;
import com.arabadzhiev.site.service.SubService;
import com.arabadzhiev.site.service.UserService;

@Controller
@RequestMapping("/")
public class SubController {
	
	@Autowired private SubService subService;
	@Autowired private UserService userService;
	@Autowired private SessionRegistry sessionRegistry;
	
	@RequestMapping
	public ModelAndView listSubs(Map<String, Object> model) {
		model.put("subs", subService.getSubs());
		model.put("usersOnline", sessionRegistry.getAllPrincipals().size());
		model.put("userCount", userService.getCount());
		model.put("searchForm", new SearchForm());
		
		return new ModelAndView("sub/list");
	}
	
	@RequestMapping("sub/{subUrl}")
	public ModelAndView listThreads(Map<String, Object> model, @PathVariable("subUrl") String subUrl) {
		model.put("threads", subService.getSub(subUrl).getThreads());
		model.put("subUrl", subUrl);
		model.put("subName", subService.getSub(subUrl).getName());
		model.put("usersOnline", sessionRegistry.getAllPrincipals().size());
		model.put("userCount", userService.getCount());
		model.put("searchForm", new SearchForm());
		
		return new ModelAndView("thread/list");
	}

}
