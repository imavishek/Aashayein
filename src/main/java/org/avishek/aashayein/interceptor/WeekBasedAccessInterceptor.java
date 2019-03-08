package org.avishek.aashayein.interceptor;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component(value = "weekBasedAccessInterceptor")
public class WeekBasedAccessInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LogManager.getLogger(WeekBasedAccessInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Calendar cal = Calendar.getInstance();

		// Getting the day on which request is made
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		// If request is made on sunday (dayOfWeek == 1)
		if (dayOfWeek == 1) {

			logger.warn("Request is made on sunday");
			response.sendRedirect(request.getContextPath() + "/Maintenance/showMaintenancePage.abhi");

			return false;
		}

		return true;
	}

}
