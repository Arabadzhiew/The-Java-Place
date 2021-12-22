package com.arabadzhiev.site.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.arabadzhiev.site.entity.Thread;

import com.arabadzhiev.site.service.ThreadService;
import com.arabadzhiev.site.service.UserService;

@Controller
@RequestMapping("search")
public class SearchController {
	
	@Autowired private ThreadService threadService;
	@Autowired private UserService userService;
	@Autowired private SessionRegistry sessionRegistry;
		
	@RequestMapping(value = "threads", method = RequestMethod.GET)
	public ModelAndView searchThread(Map<String, Object> model, SearchForm searchForm, 
			Pageable pageable, @Param("query") String query) {
		Page<Thread> results = threadService.searchThread(searchForm.getQuery(), pageable);
		model.put("results", results);
		model.put("hasResults", results.hasContent());
		model.put("usersOnline", sessionRegistry.getAllPrincipals().size());
		model.put("userCount", userService.getCount());
		model.put("username", SecurityContextHolder.getContext().getAuthentication().getName());
		model.put("query", query);
		
		return new ModelAndView("search/threads");
	}
	
	
	public static class SearchForm{
		private String query;

		public String getQuery() {
			return query;
		}

		public void setQuery(String query) {
			this.query = query;
		}
		
	}
}
