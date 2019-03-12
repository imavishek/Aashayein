package org.avishek.aashayein.eventListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.dto.MailRequestTO;
import org.avishek.aashayein.entities.Employee;
import org.avishek.aashayein.event.SendResetPasswordEvent;
import org.avishek.aashayein.utility.MailUtil;
import org.avishek.aashayein.utility.ServerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SendResetPasswordEventListener implements ApplicationListener<SendResetPasswordEvent> {

	@Autowired
	private MailUtil mailUtil;

	@Autowired
	private ServerUtil serverUtil;

	private static final Logger logger = LogManager.getLogger(SendResetPasswordEventListener.class);

	@Override
	public void onApplicationEvent(SendResetPasswordEvent event) {

		String confirmationUrl = null;
		Employee employee = event.getEmployee();
		MailRequestTO mailRequestTo = new MailRequestTO();

		confirmationUrl = serverUtil.getServerUrl() + "EmployeeProfile/Reset/showResetPassword.abhi?token="
				+ employee.getTokenUUID();

		logger.info("Send Reset Password Link Event Published");
		logger.debug("Sending Mail...");

		mailRequestTo.setRecipientName(employee.getFirstName());
		mailRequestTo.setEmailTo(employee.getEmail());
		mailRequestTo.setEmailSubject("Aashayein - Reset Password");
		mailRequestTo.setEmailForm("aashayein2019@gmail.com");
		mailRequestTo.setEmailTemplateName("resetPasswordLink.ftl");
		mailRequestTo.setUrl(confirmationUrl);
		mailRequestTo.setDetails(employee);

		Boolean success = mailUtil.sendEmail(mailRequestTo);

		if (success) {
			logger.debug("Email Sent...");
		} else {
			logger.debug("Fail To Send Mail...");
		}
	}

}
