/**
 * @Project_Name Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.configuration;
 * @File_Name SpringSecurityConfiguration.java
 * @Created_Date 11-Dec-2018
 * Modified by @author avishekdas last on 2018-12-11 01:31:24
 */

package org.avishek.aashayein.configuration;

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
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	PersistentTokenRepository tokenRepository;

	@Autowired
	private AuthenticationSuccessHandler successHandler;

	@Autowired
	private AuthenticationFailureHandler failureHandler;

	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;


	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Autowired
	private SecurityContextRepository securityContextRepository;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
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
				.antMatchers("/Maintenance/**").permitAll()
				.antMatchers("/EmployeeProfile/Reset/**").permitAll()
				.antMatchers("/EmployeeProfile/Active/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.securityContext()
				.securityContextRepository(securityContextRepository)
				.and()
			.formLogin()
				.usernameParameter("username")
				.passwordParameter("password")
				.loginPage("/Login/showLogin.abhi")
				.loginProcessingUrl("/Login/doLogin.abhi")
				/* .defaultSuccessUrl("/home", true) */
				/* .failureUrl("/Login/showLogin.abhi?error") */
				.successHandler(successHandler)
				.failureHandler(failureHandler)
				.and()
			.logout()
				.logoutUrl("/Login/logout.abhi")
				/* .logoutSuccessUrl("/Login/showLogin.abhi?logout") */
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessHandler(logoutSuccessHandler)
				/*
				 * .deleteCookies(Base64.getEncoder().encodeToString("rememberMeCookiesAvishek".
				 * getBytes()))
				 */
				.and()
			/*
			 * base64(username + ":" + expirationTime + ":" + md5Hex(username + ":" +
			 * expirationTime + ":" password + ":" + key))
			 * 
			 * 1) username: As identifiable to the UserDetailsService 2) password: That
			 * matches the one in the retrieved UserDetails 3) expirationTime: The date and
			 * time when the remember-me token expires, expressed in milliseconds 4) key: A
			 * private key to prevent modification of the remember-me token
			 */
			.rememberMe()
				.key("$2y$12$ia5x2GGvcthvT3NCx3wSzuqlSdGOEirUmLweUw25cAP0L9bISazPy")
				.rememberMeParameter("rememberMe")
				/*
				 * .rememberMeCookieName(Base64.getEncoder().encodeToString(
				 * "rememberMeCookiesAvishek".getBytes()))
				 */
				.tokenRepository(tokenRepository)
				.tokenValiditySeconds(24 * 60 * 60)
				.and()
			.exceptionHandling()
				/* .accessDeniedPage("/Login/showAccessDeniedPage.abhi") */
				.accessDeniedHandler(accessDeniedHandler)
				.and()
			.csrf();

	}

	// Adding BCryptPasswordEncoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		tokenRepositoryImpl.setDataSource(dataSource);
		return tokenRepositoryImpl;
	}
	*/

	@Bean
	public HttpSessionSecurityContextRepository httpSessionSecurityContextRepository() {
		return new HttpSessionSecurityContextRepository();
	}
}
