package org.avishek.aashayein.utility;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Component;

@Component
public class CsrfTokenValidateUtil {

	public boolean validateCsrfToken(HttpServletRequest request) {

		CsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
		CsrfToken csrfToken = csrfTokenRepository.loadToken(request);
		
		if (request.getHeader("X-CSRF-TOKEN") == null) {
			return false;
		}

		return csrfToken.getToken().equals(request.getHeader("X-CSRF-TOKEN"));
	}
}
