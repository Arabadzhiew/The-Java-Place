package com.arabadzhiev.site.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.arabadzhiev.site.entity.User;
import com.arabadzhiev.site.service.ThreadCommentService;
import com.arabadzhiev.site.service.ThreadService;
import com.arabadzhiev.site.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired private UserService userService;
	@Autowired private ThreadService threadService;
	@Autowired private ThreadCommentService commentService;
	
	@Autowired private SessionRegistry sessionRegistry;
	
	@RequestMapping("{username}")
	public ModelAndView view(Map<String, Object> model, @PathVariable("username") String username) {
		
		User user = userService.loadUserByUsername(username);
		
		model.put("user", user);
		model.put("usersOnline", sessionRegistry.getAllPrincipals().size());
		model.put("usersOnlineList", sessionRegistry.getAllPrincipals());
		model.put("userCount", userService.getCount());
		model.put("lastActive", user.getLastActive().toLocalDate().until(LocalDate.now(), ChronoUnit.DAYS));
		
		return new ModelAndView("user/view");
	}
	
	@RequestMapping("{username}/threads")
	public ModelAndView threads(Map<String, Object> model, @PathVariable("username") String username, 
			Pageable pageable) {
		
		User user = userService.loadUserByUsername(username);
		
		model.put("user", user);
		model.put("usersOnline", sessionRegistry.getAllPrincipals().size());
		model.put("usersOnlineList", sessionRegistry.getAllPrincipals());
		model.put("userCount", userService.getCount());
		model.put("threads", threadService.getByUser(user, pageable));
		model.put("lastActive", user.getLastActive().toLocalDate().until(LocalDate.now(), ChronoUnit.DAYS));
		
		return new ModelAndView("user/threads");
	}
	
	@RequestMapping("{username}/comments")
	public ModelAndView comments(Map<String, Object> model, @PathVariable("username") String username, 
			Pageable pageable) {
		
		User user = userService.loadUserByUsername(username);
		
		model.put("user", user);
		model.put("usersOnline", sessionRegistry.getAllPrincipals().size());
		model.put("usersOnlineList", sessionRegistry.getAllPrincipals());
		model.put("userCount", userService.getCount());
		model.put("comments", commentService.getComments(user, pageable));
		model.put("lastActive", user.getLastActive().toLocalDate().until(LocalDate.now(), ChronoUnit.DAYS));
		
		return new ModelAndView("user/comments");
	}
}
