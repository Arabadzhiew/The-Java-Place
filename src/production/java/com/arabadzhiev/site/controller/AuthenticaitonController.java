package com.arabadzhiev.site.controller;

import java.util.Map;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticaitonController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(Map<String, Object> model) {
		
		model.put("loginForm", new LoginForm());
		return new ModelAndView("login");
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
}
