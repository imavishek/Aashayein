package org.avishek.aashayein.utility;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

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

	@Autowired
	private ServletContext servletContext;

	private static final Logger logger = LogManager.getLogger(MailSenderUtil.class);

	public Boolean sendEmail(MailRequestTO mailRequest) {

		Boolean success = false;
		String rootPath = servletContext.getRealPath("/");
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

			Template t = config.getTemplate(mailRequest.getEmailTemplateName());
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mailRequest);

			helper.setTo(new InternetAddress(mailRequest.getEmailTo()));
			helper.setFrom(new InternetAddress(mailRequest.getEmailForm()));
			helper.setText(html, true);
			helper.addInline("logo", new File(rootPath + "/assets/img/logos/Aashayein.png"));
			helper.addInline("google", new File(rootPath + "/assets/img/ico_google.png"));
			helper.addInline("facebook", new File(rootPath + "/assets/img/ico_facebook.png"));
			helper.addInline("twitter", new File(rootPath + "/assets/img/ico_twitter.png"));
			helper.addInline("instagram", new File(rootPath + "/assets/img/ico_instagram.png"));
			helper.addInline("linkedin", new File(rootPath + "/assets/img/ico_linkedin.png"));
			helper.setSubject(mailRequest.getEmailSubject());

			mailSender.send(mimeMessage);

			logger.info("Mail Sent Successfully. To:- " + mailRequest.getRecipientName() + "<"
					+ mailRequest.getEmailTo() + ">," + " Subject:-" + mailRequest.getEmailSubject());
			success = true;

		} catch (MessagingException | IOException | TemplateException e) {

			logger.error("Fail To Send Mail. To:- " + mailRequest.getRecipientName() + "<" + mailRequest.getEmailTo()
					+ ">," + " Subject:-" + mailRequest.getEmailSubject());

			logger.error(e.getMessage() + " [Exception " + e.getClass() + "]");

			success = false;
		}

		return success;
	}

}
