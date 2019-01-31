package org.avishek.aashayein.utility;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.dto.MailRequestTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class MailSenderUtil {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Configuration config;

	private static final Logger logger = LogManager.getLogger(MailSenderUtil.class);

	public Boolean sendEmail(MailRequestTO mailRequest) {

		Boolean success = false;
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

			Template t = config.getTemplate(mailRequest.getEmailTemplateName());
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mailRequest);

			helper.setTo(mailRequest.getEmailTo());
			helper.setFrom(mailRequest.getEmailForm());
			helper.setCc(mailRequest.getEmailCC());
			helper.setBcc(mailRequest.getEmailBCC());
			helper.setText(html, true);
			helper.setSubject(mailRequest.getEmailSubject());

			mailSender.send(mimeMessage);

			logger.info("Mail Sent Successfully. To:- <" + mailRequest.getEmailTo() + ">"
					+ mailRequest.getRecipientName() + " Subject:-" + mailRequest.getEmailSubject());
			success = true;

		} catch (MessagingException | IOException | TemplateException e) {

			logger.error("Fail To Send Mail. To:- <" + mailRequest.getEmailTo() + ">" + mailRequest.getRecipientName()
					+ " Subject:-" + mailRequest.getEmailSubject());

			logger.error(e.getMessage() + " [Exception " + e.getClass() + "]");

			success = false;
		}

		return success;
	}

}
