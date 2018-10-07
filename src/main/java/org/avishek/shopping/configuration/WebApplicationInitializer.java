/**
 * @Project_Name shopping
 * © @Author avishekdas
 * package org.avishek.shopping.configuration;
 * @File_Name WebApplicationInitializer.java
 * @Created_Date 05-Oct-2018
 * Modified by @author avishekdas last on 2018-10-05 23:59:02
 */

package org.avishek.shopping.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {

		return new Class[] {SpringConfiguration.class};
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[] {SpringConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[] {"*.abhi"};
	}
	
}