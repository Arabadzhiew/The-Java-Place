package com.arabadzhiev.site.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.arabadzhiev.site.controller.SearchController.SearchForm;
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
	public ModelAndView view(Map<String, Object> model, @PathVariable("username") String username, @Param("error") String error) {
		User user = userService.loadUserByUsername(username);
		
		if(error != null) {
			model.put("error", "");
		}
		model.put("user", user);
		model.put("usersOnline", sessionRegistry.getAllPrincipals().size());
		model.put("usersOnlineList", sessionRegistry.getAllPrincipals());
		model.put("userCount", userService.getCount());
		model.put("username", SecurityContextHolder.getContext().getAuthentication().getName());
		model.put("lastActive", user.getLastActive().toLocalDate().until(LocalDate.now(), ChronoUnit.DAYS));
		model.put("searchForm", new SearchForm());
		
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
		model.put("username", SecurityContextHolder.getContext().getAuthentication().getName());
		model.put("threads", threadService.getByUser(user, pageable));
		model.put("lastActive", user.getLastActive().toLocalDate().until(LocalDate.now(), ChronoUnit.DAYS));
		model.put("searchForm", new SearchForm());
		
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
		model.put("username", SecurityContextHolder.getContext().getAuthentication().getName());
		model.put("comments", commentService.getComments(user, pageable));
		model.put("lastActive", user.getLastActive().toLocalDate().until(LocalDate.now(), ChronoUnit.DAYS));
		model.put("searchForm", new SearchForm());
		
		return new ModelAndView("user/comments");
	}
	
	@RequestMapping(value = "{username}", method = RequestMethod.POST)
	public RedirectView changeProfileImg(@RequestParam("image") MultipartFile image, 
			@PathVariable("username") String username) throws IOException {
		if(!(image.getContentType().equalsIgnoreCase("image/jpeg") || 
				image.getContentType().equalsIgnoreCase("image/jpg") || 
				image.getContentType().equalsIgnoreCase("image/png"))){
			return new RedirectView("/user/"+username+"?error", true);
		}
		User user = userService.loadUserByUsername(username);
		user.setProfileImage(image.getBytes());
		userService.updateUser(user);
		
		return new RedirectView("/user/"+username, true);
	}
	
	@RequestMapping(value = "{username}/delete/profileImage", method = RequestMethod.POST)
	public RedirectView deleteProfileImg(@PathVariable("username") String username) {
		User user = userService.loadUserByUsername(username);
		user.setProfileImage(null);
		userService.updateUser(user);
		
		return new RedirectView("/user/"+username, true);
	}
}
