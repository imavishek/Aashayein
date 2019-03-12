package org.avishek.aashayein.eventListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.dto.MailRequestTO;
import org.avishek.aashayein.entities.Employee;
import org.avishek.aashayein.event.SendActivationLinkEvent;
import org.avishek.aashayein.utility.MailUtil;
import org.avishek.aashayein.utility.ServerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SendActivationLinkEventListener implements ApplicationListener<SendActivationLinkEvent> {

	@Autowired
	private MailUtil mailUtil;

	@Autowired
	private ServerUtil serverUtil;

	private static final Logger logger = LogManager.getLogger(SendActivationLinkEventListener.class);

	@Override
	public void onApplicationEvent(SendActivationLinkEvent event) {

		String confirmationUrl = null;
		Employee employee = event.getEmployee();
		MailRequestTO mailRequestTo = new MailRequestTO();

		confirmationUrl = serverUtil.getServerUrl() + "EmployeeProfile/Active/showSetPassword.abhi?token="
				+ employee.getTokenUUID();

		logger.info("Send Activation Link Event Published");
		logger.debug("Sending Mail...");

		mailRequestTo.setRecipientName(employee.getFirstName());
		mailRequestTo.setEmailTo(employee.getEmail());
		mailRequestTo.setEmailSubject("Aashayein - Active Account");
		mailRequestTo.setEmailForm("aashayein2019@gmail.com");
		mailRequestTo.setEmailTemplateName("activationLink.ftl");
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
