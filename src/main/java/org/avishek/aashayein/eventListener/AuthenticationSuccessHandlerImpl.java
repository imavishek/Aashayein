package org.avishek.aashayein.eventListener;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.dto.EmployeeDetails;
import org.avishek.aashayein.entities.Employee;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	private static final Logger logger = LogManager.getLogger(AuthenticationSuccessHandlerImpl.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		EmployeeDetails employee = (EmployeeDetails) authentication.getPrincipal();
		Employee user = employee.getUser();

		logger.info("User: " + user.getFullName() + " Having EmployeeCode: " + user.getEmployeeCode()
				+ " Logged In Successfully");

		HttpSession session = request.getSession();
		session.setAttribute("id", user.getEmployeeId());
		session.setAttribute("empCode", user.getEmployeeCode());
		session.setAttribute("name", user.getFullName());
		session.setAttribute("profilePhoto", user.getProfilePhoto());
		session.setAttribute("gender", user.getGender());

		response.sendRedirect(request.getContextPath() + "/home");
	}

}
