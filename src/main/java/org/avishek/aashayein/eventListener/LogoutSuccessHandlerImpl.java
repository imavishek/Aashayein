package org.avishek.aashayein.eventListener;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

	private static final Logger logger = LogManager.getLogger(LogoutSuccessHandlerImpl.class);

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		logger.info("User Has Been Logged Out Successfully");

		response.sendRedirect(request.getContextPath() + "/Login/showLogin.abhi?logout");

	}

}
