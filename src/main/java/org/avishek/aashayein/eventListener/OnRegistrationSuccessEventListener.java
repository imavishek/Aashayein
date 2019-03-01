package org.avishek.aashayein.eventListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.dto.MailRequestTO;
import org.avishek.aashayein.entities.Employee;
import org.avishek.aashayein.event.OnRegistrationSuccessEvent;
import org.avishek.aashayein.utility.MailUtil;
import org.avishek.aashayein.utility.ServerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OnRegistrationSuccessEventListener implements ApplicationListener<OnRegistrationSuccessEvent> {

	@Autowired
	private MailUtil mailUtil;

	@Autowired
	private ServerUtil serverUtil;

	private static final Logger logger = LogManager.getLogger(OnRegistrationSuccessEventListener.class);

	@Override
	public void onApplicationEvent(OnRegistrationSuccessEvent event) {

		String confirmationUrl = null;
		Employee employee = event.getEmployee();
		MailRequestTO mailRequestTo = new MailRequestTO();

		confirmationUrl = serverUtil.getServerUrl() + "EmployeeProfile/Reset/showSetPassword.abhi?token="
				+ employee.getTokenUUID();

		logger.info("On Registration Success Event Published");
		logger.debug("Sending Mail...");

		mailRequestTo.setRecipientName(employee.getFirstName());
		mailRequestTo.setEmailTo(employee.getEmail());
		mailRequestTo.setEmailSubject("Aashayein - Active Account");
		mailRequestTo.setEmailForm("aashayein2019@gmail.com");
		mailRequestTo.setEmailTemplateName("email.ftl");
		mailRequestTo.setUrl(confirmationUrl);
		mailRequestTo.setDetails(employee);

		Boolean success = mailUtil.sendEmail(mailRequestTo);

		if (success) {
			logger.debug("Email Sent...");
		} else {
			logger.debug("Failure Sending Mail...");
		}
	}

}
