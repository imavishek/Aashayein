package org.avishek.aashayein.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.utility.CsrfTokenValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component(value = "ajaxCallValidateInterceptor")
public class AjaxCallValidateInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	CsrfTokenValidateUtil csrfTokenValidateUtil;

	private static final Logger logger = LogManager.getLogger(AjaxCallValidateInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (!csrfTokenValidateUtil.validateCsrfToken(request)) {
			logger.warn("Invalid CSRFToken for ajax call:- " + request.getRequestURL().toString());
			response.sendRedirect(request.getContextPath() + "/Login/showAccessDeniedPage.abhi");

			return false;
		}

		return true;
	}

}
