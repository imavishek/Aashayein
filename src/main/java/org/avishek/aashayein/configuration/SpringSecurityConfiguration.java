/**
 * @Project_Name Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.configuration;
 * @File_Name SpringSecurityConfiguration.java
 * @Created_Date 11-Dec-2018
 * Modified by @author avishekdas last on 2018-12-11 01:31:24
 */

package org.avishek.aashayein.configuration;

import java.util.Base64;

import javax.sql.DataSource;

import org.avishek.aashayein.eventListener.AuthenticationSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationSuccessHandlerImpl successHandler;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/Production/**").hasRole("Production")
				.antMatchers("/Sales/**").hasRole("Sales")
				.antMatchers("/Accounting/**").hasRole("Accounting")
				.antMatchers("/Products/**").hasRole("Products")
				.antMatchers("/Admin/**").hasRole("Admin")
				.antMatchers("/Login/**").permitAll()
				.antMatchers("/EmployeeProfile/Reset/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/Login/showLogin.abhi")
				.loginProcessingUrl("/Login/doLogin.abhi") 
				.defaultSuccessUrl("/home", true)
				.failureUrl("/Login/showLogin.abhi?error")
				.successHandler(successHandler)
				.and()
			.logout()
				.logoutUrl("/Login/logout.abhi")
				.logoutSuccessUrl("/Login/showLogin.abhi?logout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				/*
				 * .deleteCookies(Base64.getEncoder().encodeToString("rememberMeCookiesAvishek".
				 * getBytes()))
				 */
				.and()
			.rememberMe()
				.key("$2y$12$1PrxhSySDhg.f17cQkAIleYnFjRh9o0nZg1Nr6aso2Jt36nmaWr0C")
				.rememberMeParameter("rememberMe")
				/*
				 * .rememberMeCookieName(Base64.getEncoder().encodeToString(
				 * "rememberMeCookiesAvishek".getBytes()))
				 */
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(24 * 60 * 60)
				.and()
			.exceptionHandling()
				.accessDeniedPage("/Login/showAccessDeniedPage.abhi")
				.and()
			.csrf().disable();
			
	}

	// Adding BCryptPasswordEncoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		tokenRepositoryImpl.setDataSource(dataSource);
		return tokenRepositoryImpl;
	}
}
