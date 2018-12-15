/**
 * @Project_Name Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.configuration;
 * @File_Name SpringSecurityConfiguration.java
 * @Created_Date 11-Dec-2018
 * Modified by @author avishekdas last on 2018-12-11 01:31:24
 */

package org.avishek.aashayein.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		UserBuilder users = User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication()
		.withUser(users.username("8908").password("mindfire").roles("EMPLOYEE"))
		.withUser(users.username("8909").password("mindfire").roles("ADMIN"));
	}
	
	

}
