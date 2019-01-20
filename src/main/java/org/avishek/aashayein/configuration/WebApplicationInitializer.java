/**
 * @Project_Name Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.configuration;
 * @File_Name WebApplicationInitializer.java
 * @Created_Date 05-Oct-2018
 * Modified by @author avishekdas last on 2018-10-05 23:59:02
 */

package org.avishek.aashayein.configuration;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {

		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class[] { SpringConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {

		return new String[] { "/" };
	}

	@Override
	protected FrameworkServlet createDispatcherServlet(WebApplicationContext wac) {
		DispatcherServlet ds = new DispatcherServlet(wac);
		/*
		 * setting this flag to true will throw NoHandlerFoundException instead of 404
		 * page
		 */
		ds.setThrowExceptionIfNoHandlerFound(true);
		return ds;
	}

}