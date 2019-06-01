package org.avishek.aashayein.utility;

import java.net.URI;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.dto.GoogleCaptchaResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Component
public class CaptchaUtil {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CaptchaSettings captchaSettings;

	private static Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

	private static final Logger logger = LogManager.getLogger(CaptchaUtil.class);

	public String validateCaptcha(String recaptcha_response) {

		String message = "";

		if (!(StringUtils.hasLength(recaptcha_response) && RESPONSE_PATTERN.matcher(recaptcha_response).matches())) {

			message = "Invalid Recaptcha";

			return message;

		}

		URI verifyUri = URI.create(
				String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s",
						captchaSettings.getSecretKey(), recaptcha_response, getClientIP()));

		GoogleCaptchaResponseTO googleResponse = restTemplate.getForObject(verifyUri, GoogleCaptchaResponseTO.class);
		logger.info("Google Re-Captcha Response:- " + googleResponse);

		if (!googleResponse.isSuccess()) {
			message = "ReCaptcha was not successfully validated";

			return message;
		}

		return message;
	}

	private String getClientIP() {
		final String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}
}
