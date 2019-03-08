/**
 * @Project_Name Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.configuration;
 * @File_Name WebApplicationInitializer.java
 * @Created_Date 05-Oct-2018
 * Modified by @author avishekdas last on 2018-10-05 23:59:02
 */

package org.avishek.aashayein.configuration;

import javax.servlet.ServletContext;

import org.avishek.aashayein.eventListener.SessionListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/*
	 * https://stackoverflow.com/questions/35258758/getservletconfigclasses-vs-
	 * getrootconfigclasses-when-extending-abstractannot
	 */

	/*
	 * The root WebApplicationContext typically contains infrastructure beans, such
	 * as data repositories and business services that need to be shared across
	 * multiple Servlet instances.
	 */

	/*
	 * Root Config Classes will be loaded first and then Servlet Config Classes will
	 * be loaded.
	 */

	/*
	 * Root Config Classes will be the Parent Context and it will create a
	 * ApplicationContext instace. Where as Servlet Config Classes will be the Child
	 * Context of the Parent Context and it will create a WebApplicationContext
	 * instance.
	 */

	@Override
	protected Class<?>[] getRootConfigClasses() {

		return new Class[] { SpringConfiguration.class, SpringSecurityConfiguration.class, WebConfig.class };
	}

	/*
	 * Servlet Config Classes are actually used to Create Beans which are
	 * DispatcherServlet specific such as ViewResolvers, ArgumentResolvers,
	 * Interceptor, etc.
	 */

	/*
	 * Since we have a single DispatcherServlet here, we can add the WebConfig to
	 * the root context and make the servlet context empty:
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {

		return null;
	}

	/*
	 * One of the canonical examples of using hierarchical ApplicationContext is
	 * when we have multiple DispatcherServlets in a web application and we're going
	 * to share some of the common beans such as datasources between them. This way,
	 * we can define a root ApplicationContext that contain all the common beans and
	 * multiple WebApplicationContexts that inherit the common beans from the root
	 * context.
	 */
	@Override
	protected String[] getServletMappings() {

		return new String[] { "/" };
	}

	@Override
	protected void registerDispatcherServlet(ServletContext servletContext) {

		super.registerDispatcherServlet(servletContext);

		servletContext.addListener(new SessionListener());

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