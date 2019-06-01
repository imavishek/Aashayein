package org.avishek.aashayein.repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.avishek.aashayein.dto.EmployeeDetails;
import org.avishek.aashayein.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class RefreshingUserDetailsSecurityContextRepository implements SecurityContextRepository {

	@Autowired
	private HttpSessionSecurityContextRepository delegate;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public SecurityContext loadContext(final HttpRequestResponseHolder requestResponseHolder) {

		SecurityContext securityContext = delegate.loadContext(requestResponseHolder);

		if (securityContext.getAuthentication() == null) {
			return securityContext;
		}

		Authentication principal = securityContext.getAuthentication();
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		EmployeeDetails employeeDetails = (EmployeeDetails) userDetails;
		Employee user = employeeDetails.getUser();

		/*
		 * RememberMeAuthenticationToken token = new RememberMeAuthenticationToken(
		 * "$2y$12$ia5x2GGvcthvT3NCx3wSzuqlSdGOEirUmLweUw25cAP0L9bISazPy", principal,
		 * userDetails.getAuthorities());
		 */

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

		/*
		 * System.out.println("2--" +
		 * requestResponseHolder.getRequest().getSession().getAttribute("name"));
		 * 
		 * if (requestResponseHolder.getRequest().getSession().getAttribute("id") ==
		 * null) { requestResponseHolder.getRequest().getSession().setAttribute("id",
		 * user.getEmployeeId());
		 * requestResponseHolder.getRequest().getSession().setAttribute("empCode",
		 * user.getEmployeeCode());
		 * requestResponseHolder.getRequest().getSession().setAttribute("name",
		 * user.getFullName());
		 * requestResponseHolder.getRequest().getSession().setAttribute("profilePhoto",
		 * user.getProfilePhoto());
		 * requestResponseHolder.getRequest().getSession().setAttribute("gender",
		 * user.getGender()); }
		 */

		securityContext.setAuthentication(token);
		saveContext(securityContext, requestResponseHolder.getRequest(), requestResponseHolder.getResponse());

		return securityContext;
	}

	@Override
	public void saveContext(final SecurityContext context, final HttpServletRequest request,
			final HttpServletResponse response) {
		delegate.saveContext(context, request, response);
	}

	@Override
	public boolean containsContext(final HttpServletRequest request) {
		return delegate.containsContext(request);
	}
}