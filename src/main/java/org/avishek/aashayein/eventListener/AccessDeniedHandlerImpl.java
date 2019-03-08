package org.avishek.aashayein.eventListener;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.dto.EmployeeDetails;
import org.avishek.aashayein.entities.Employee;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

	private static final Logger logger = LogManager.getLogger(AccessDeniedHandlerImpl.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			EmployeeDetails employee = (EmployeeDetails) auth.getPrincipal();
			Employee user = employee.getUser();
			logger.warn("User: " + user.getFullName() + " Having EmployeeCode: " + user.getEmployeeCode()
					+ " attempted to access the protected URL: " + request.getRequestURL().toString());
		}

		response.sendRedirect(request.getContextPath() + "/Login/showAccessDeniedPage.abhi");

	}
}