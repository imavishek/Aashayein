/**
 * @Project_Name Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.configuration;
 * @File_Name SecurityWebApplicationInitializer.java
 * @Created_Date 05-Oct-2018
 * Modified by @author avishekdas last on 2018-10-05 23:59:02
 */

package org.avishek.aashayein.configuration;

import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
	// Registering the Spring Security Filter

	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		insertFilters(servletContext, new MultipartFilter());
	}
}