package com.arabadzhiev.site.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.arabadzhiev.site.entity.User;
import com.arabadzhiev.site.entity.UserAuthority;
import com.arabadzhiev.site.service.UserService;
import com.arabadzhiev.site.validation.EmailExists;
import com.arabadzhiev.site.validation.UniqueEmail;


@Controller
public class AuthenticaitonController {
	
	@Autowired UserService userService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(Map<String, Object> model, HttpServletRequest request) {
		if(SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
			return new ModelAndView(new RedirectView("/", true));
		}
		String referer = request.getHeader("Referer");
		if(referer != null) {
			request.getSession().setAttribute("urlPriorLogin", referer);
		}
		model.put("loginForm", new LoginForm());
		model.put("recoveryForm", new RecoveryForm());
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET, params= {"error"})
	public ModelAndView login(Map<String, Object> model, @Param("error") String error) {
		if(SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
			return new ModelAndView(new RedirectView("/", true));
		}
		model.put("error", "");
		model.put("loginForm", new LoginForm());
		model.put("recoveryForm", new RecoveryForm());
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
	
	@RequestMapping(value = "recovery", method = RequestMethod.GET)
	public RedirectView generateRecoveryKey() {
		return new RedirectView("/", true);
	}
	
	@RequestMapping(value = "recovery", method = RequestMethod.POST)
	public RedirectView generateRecoveryKey(Map<String, Object> model, RedirectAttributes redirectAttributes, 
			@Valid RecoveryForm recoveryForm, Errors errors) {
		if(!errors.hasErrors()) {
			userService.generateRecoveryKey(recoveryForm.getEmail());
			redirectAttributes.addFlashAttribute("recoverySuccess", "");
			new RedirectView("/login", true);
		}
		boolean notBlank = false;
		boolean email = false;
		boolean emailExists = false;
		for(ObjectError e: errors.getAllErrors()) {
			String code = e.getCode();
			if(code.equals("NotBlank")) {
				notBlank = true;
			}else if(code.equals("Email")){
				email = true;
			}else if(code.equals("EmailExists")) {
				emailExists = true;
			}
		}
		String error = "";
		if(notBlank == true) {
			error = "This field cannot be blank";
		}else if(email == true) {
			error ="Please enter a valid email";
		}else if(emailExists == true) {
			error = "There is no user registered with this email";
		}
		redirectAttributes.addFlashAttribute("recoveryError", error);
		return new RedirectView("/login", true);
	}
	
	@RequestMapping(value= "recovery/{recoveryKey}", method = RequestMethod.GET)
	public ModelAndView passwordRecovery(Map<String, Object> model, @PathVariable("recoveryKey") String recoveryKey) {
		if(userService.recoveryKeyValid(recoveryKey)) {
			model.put("passwordChangeForm", new PasswordChangeForm());
			return new ModelAndView("recovery");
		}
		return new ModelAndView(new RedirectView("/login",true));
	}
	@RequestMapping(value= "recovery/{recoveryKey}", method = RequestMethod.POST)
	public ModelAndView passwordRecovery(Map<String, Object> model, @Valid PasswordChangeForm passwordChangeForm, 
			Errors errors, @PathVariable("recoveryKey") String recoveryKey) {
		if(errors.hasErrors()) {
			return new ModelAndView("recovery");
		}else if(!passwordChangeForm.getPassword().equals(passwordChangeForm.getConfirmPassword())) {
			model.put("passwordsDontMatch", true);
			return new ModelAndView("recovery");
		}
		
		userService.changePassword(recoveryKey, passwordChangeForm.getPassword());
		return new ModelAndView(new RedirectView("/login", true));
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
		@UniqueEmail(message = "A user is already registered with this email")
		private String email;
		@Size(min = 10, message = "The password must be at least 10 characters long")
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
	
	public static class RecoveryForm{
		@NotBlank(message = "This field cannot be blank")
		@Email(message = "Please enter a valid email")
		@EmailExists(message = "There is no user registered with this email")
		private String email;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}
	
	public static class PasswordChangeForm{
		@Size(min = 10, message = "The password must be at least 10 characters long")
		@NotBlank(message = "Please enter a password")
		private String password;
		@NotBlank(message = "Please double check your password")
		private String confirmPassword;
		
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
