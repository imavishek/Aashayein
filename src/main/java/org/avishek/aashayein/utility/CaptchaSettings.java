package org.avishek.aashayein.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "captchaSettings")
public class CaptchaSettings {

	@Value("${google.recaptcha.key.site}")
	private String siteKey;

	@Value("${google.recaptcha.key.secret}")
	private String secretKey;

	public String getSiteKey() {
		return siteKey;
	}

	public void setSiteKey(String siteKey) {
		this.siteKey = siteKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	@Override
	public String toString() {
		return "CaptchaSettings [siteKey=" + siteKey + ", secretKey=" + secretKey + "]";
	}

}
