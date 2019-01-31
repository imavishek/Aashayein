package org.avishek.aashayein.eventHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.dto.MailRequestTO;
import org.avishek.aashayein.event.OnRegistrationCompleteEvent;
import org.avishek.aashayein.utility.MailSenderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OnRegistrationCompleteEventListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	
	@Autowired
	private MailSenderUtil mailSenderUtil;

	private static final Logger logger = LogManager.getLogger(OnRegistrationCompleteEventListener.class);

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {

		EmployeeTO employeeTo = event.getEmployeeTo();
		MailRequestTO mailRequestTo = new MailRequestTO();

		logger.info("On Registration Complete Event Published");
		logger.debug("Sending Mail...");

		mailRequestTo.setRecipientName(employeeTo.getFirstName() + " " + employeeTo.getLastName());
		mailRequestTo.setEmailTo(employeeTo.getEmail());
		mailRequestTo.setEmailSubject("Your EmailId Registered With Aashayein Successfully");
		mailRequestTo.setEmailForm("aashayein2019@gmail.com");
		mailRequestTo.setEmailTemplateName("email.ftl");
		
		Boolean success = mailSenderUtil.sendEmail(mailRequestTo);
		
		if(success) {
			logger.debug("Email Sent...");
		} else {
			logger.debug("Failure Sending Mail...");
		}
	}

}
