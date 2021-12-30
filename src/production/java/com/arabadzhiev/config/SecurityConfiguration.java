package com.arabadzhiev.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.arabadzhiev.site.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,
							order = 0,
							mode = AdviceMode.PROXY, proxyTargetClass = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired UserService userService;
	
	@Bean 
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	@Bean
	protected SessionRegistry sessionRegistryImpl() {
		return new SessionRegistryImpl();
	}
	
	@Bean
	public SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler() {
		CustomAuthenticationSuccessHandler authenticationSuccessHandler = new CustomAuthenticationSuccessHandler("/");
		authenticationSuccessHandler.setUseReferer(true);
		
		return authenticationSuccessHandler;
	}
	
	@Bean
	public SimpleUrlLogoutSuccessHandler logoutSuccessHandler() {
		SimpleUrlLogoutSuccessHandler logoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
		logoutSuccessHandler.setUseReferer(true);
		
		return logoutSuccessHandler;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception{
		builder
			.userDetailsService(this.userService)
				.passwordEncoder(new BCryptPasswordEncoder())
			.and()
			.eraseCredentials(true);
	}
	
	@Override
	public void configure(WebSecurity security) {
		security.ignoring().antMatchers("/resources/**");
	}
	
	@Override
	protected void configure(HttpSecurity security) throws Exception{
		security
			.requiresChannel().antMatchers("/**").requiresSecure()
			.and().authorizeRequests()
				.antMatchers("/sub/*/thread/create").authenticated()
				.antMatchers("/sub/*/thread/edit").authenticated()
				.antMatchers("/signup").anonymous()
				.antMatchers("/recovery").anonymous()
				.antMatchers("/recovery/*").anonymous()
				.antMatchers("/user/exists").anonymous()
				.anyRequest().permitAll()
			.and().formLogin()
				.loginPage("/login").failureUrl("/login?error")
				.usernameParameter("username")
				.passwordParameter("password")
				.successHandler(this.authenticationSuccessHandler())
				.permitAll()
			.and().logout()
				.logoutUrl("/logout").logoutSuccessHandler(this.logoutSuccessHandler())
				.invalidateHttpSession(true).deleteCookies("JSESSIONID")
				.permitAll()
			
			.and().sessionManagement()
				.sessionFixation().changeSessionId()
				.maximumSessions(20).maxSessionsPreventsLogin(true)
				.sessionRegistry(this.sessionRegistryImpl());
	}
	
	public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
		public CustomAuthenticationSuccessHandler(String defaultUrl) {
			super.setDefaultTargetUrl(defaultUrl);
		}
		
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws ServletException, IOException {
			HttpSession session = request.getSession(false);
			if(session != null) {
				String redirectUrl = (String)session.getAttribute("urlPriorLogin");
				if(redirectUrl != null) {
					session.removeAttribute("urlPriorLogin");
					super.getRedirectStrategy().sendRedirect(request, response, redirectUrl);
				}else {
					super.onAuthenticationSuccess(request, response, authentication);
				}
			}else {
				super.onAuthenticationSuccess(request, response, authentication);
			}
		}
	}
}
