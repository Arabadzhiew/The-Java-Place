package com.arabadzhiev.config;

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
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.arabadzhiev.site.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,
							order = 0,
							mode = AdviceMode.PROXY, proxyTargetClass = false
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
			.authorizeRequests()
				.antMatchers("/signup").anonymous()
				.anyRequest().authenticated()
			.and().formLogin()
				.loginPage("/login").failureUrl("/login?error")
				.defaultSuccessUrl("/")
				.usernameParameter("username")
				.passwordParameter("password")
				.permitAll()
			.and().logout()
				.logoutUrl("/logout").logoutSuccessUrl("/login?loggedOut")
				.invalidateHttpSession(true).deleteCookies("JSESSIONID")
				.permitAll()
			.and().sessionManagement()
				.sessionFixation().changeSessionId()
				.maximumSessions(20).maxSessionsPreventsLogin(true)
				.sessionRegistry(this.sessionRegistryImpl());
	}
}
