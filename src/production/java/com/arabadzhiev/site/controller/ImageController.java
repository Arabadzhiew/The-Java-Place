package com.arabadzhiev.site.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.arabadzhiev.site.service.UserService;

@Controller
@RequestMapping("images")
public class ImageController {
	
	@Autowired private UserService userService;
	
	@RequestMapping(value = "profile/{username}", method = RequestMethod.GET)
	public void getProfileImage(@PathVariable("username") String username, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg, image/jpg, image/png");
		response.getOutputStream().write(userService.loadUserByUsername(username).getProfileImage());
		response.getOutputStream().close();
	}
}
