package org.avishek.aashayein.dto;

public class MailRequestTO {

	private String recipientName;

	private String emailTo;

	private String emailForm;

	private String emailCC;

	private String emailBCC;

	private String emailSubject;

	private String emailTemplateName;

	private String url;

	private Object details;

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getEmailForm() {
		return emailForm;
	}

	public void setEmailForm(String emailForm) {
		this.emailForm = emailForm;
	}

	public String getEmailCC() {
		return emailCC;
	}

	public void setEmailCC(String emailCC) {
		this.emailCC = emailCC;
	}

	public String getEmailBCC() {
		return emailBCC;
	}

	public void setEmailBCC(String emailBCC) {
		this.emailBCC = emailBCC;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailTemplateName() {
		return emailTemplateName;
	}

	public void setEmailTemplateName(String emailTemplateName) {
		this.emailTemplateName = emailTemplateName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Object getDetails() {
		return details;
	}

	public void setDetails(Object details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "MailRequestTO [recipientName=" + recipientName + ", emailTo=" + emailTo + ", emailForm=" + emailForm
				+ ", emailCC=" + emailCC + ", emailBCC=" + emailBCC + ", emailSubject=" + emailSubject
				+ ", emailTemplateName=" + emailTemplateName + ", url=" + url + ", details=" + details + "]";
	}

}
