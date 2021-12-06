package com.arabadzhiev.site.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.arabadzhiev.site.entity.User;
import com.arabadzhiev.site.entity.UserAuthority;
import com.arabadzhiev.site.service.UserService;


@Controller
public class AuthenticaitonController {
	
	@Autowired UserService userService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(Map<String, Object> model) {
		if(SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
			return new ModelAndView(new RedirectView("/", true));
		}
		model.put("loginForm", new LoginForm());
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET, params= {"error"})
	public ModelAndView login(Map<String, Object> model, @Param("error") String error) {
		if(SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
			return new ModelAndView(new RedirectView("/", true));
		}
		model.put("error", "");
		model.put("loginForm", new LoginForm());
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public ModelAndView signup(Map<String, Object> model) {
		if(SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
			return new ModelAndView(new RedirectView("/", true));
		}
		
		model.put("signupForm", new SignupForm());
		return new ModelAndView("signup");
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public ModelAndView signup (Map<String, Object> model, @Valid SignupForm signupForm, Errors errors) {
		
		if(errors.hasErrors()) {
			return new ModelAndView("signup");
		}else if(!signupForm.getPassword().equals(signupForm.getConfirmPassword())) {
			model.put("passwordsDontMatch", "true");
			return new ModelAndView("signup");
		}
		
		User user = new User();
		
		user.setUsername(signupForm.getUsername());
		user.setEmail(signupForm.getEmail());
		Set<UserAuthority> userAuthorities = new HashSet<>();
		userAuthorities.add(new UserAuthority("USER"));
		user.setAuthorities(userAuthorities);
		
		userService.persistUser(user, signupForm.getPassword());
		
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, signupForm.getPassword()));
		
		return new ModelAndView(new RedirectView("/", true));
	}
	
	public static class LoginForm{
		@NotBlank(message = "Please type in your username")
		private String username;
		@NotBlank(message = "Please type in your password")
		private String password;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	}
	
	public static class SignupForm{
		@NotBlank(message = "The username field cannot be blank")
		private String username;
		@NotBlank(message = "The email field cannot be blank")
		@Email(message = "Please enter a valid email adress")
		private String email;
		@NotBlank(message = "The password field cannot be blank")
		private String password;
		@NotBlank(message = "Please double check your password")
		private String confirmPassword;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getConfirmPassword() {
			return confirmPassword;
		}
		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}
	}
}
