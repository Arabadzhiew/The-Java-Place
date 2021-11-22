package com.arabadzhiev.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception{
		builder
			.inMemoryAuthentication()
				.withUser("Petar")
				.password("{noop}123321")
				.authorities("USER", "ADMIN")
			.and()
				.withUser("User")
				.password("{noop}123321")
				.authorities("USER");
	}
	
	@Override
	public void configure(WebSecurity security) {
		security.ignoring().antMatchers("/resources/**");
	}
	
	@Override
	protected void configure(HttpSecurity security) throws Exception{
		security
			.authorizeRequests()
				.antMatchers("/sub/**/thread/delete", "/sub/**/thread/edit").hasAuthority("ADMIN")
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
		.and().csrf().disable();
	}
}
