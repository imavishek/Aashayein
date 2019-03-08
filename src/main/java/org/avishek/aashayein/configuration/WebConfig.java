package org.avishek.aashayein.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "org.avishek.aashayein.*" })
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	@Qualifier("ajaxCallValidateInterceptor")
	private HandlerInterceptorAdapter ajaxCallValidateInterceptor;

	@Autowired
	@Qualifier("weekBasedAccessInterceptor")
	private HandlerInterceptorAdapter weekBasedAccessInterceptor;

	// Creating ViewResolver Bean
	@Bean
	public ViewResolver getResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		// viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Override
	// Adding Interceptors
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("siteLanguage");
		registry.addInterceptor(localeChangeInterceptor);
		List<String> excludePathPatterns = new ArrayList<String>();
		excludePathPatterns.add("/Maintenance/*");
		excludePathPatterns.add("/assets/**");
		registry.addInterceptor(weekBasedAccessInterceptor).excludePathPatterns(excludePathPatterns);
		registry.addInterceptor(ajaxCallValidateInterceptor).addPathPatterns("/**/Asyn/**");
		/*
		 * registry.addInterceptor(new
		 * WeekBasedAccessInterceptor()).addPathPatterns("/*");
		 */
	}

	/*
	 * Any request with url mapping /assets/** will directly look for /assets
	 * folder.
	 */
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
	}
}
