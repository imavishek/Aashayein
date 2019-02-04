package org.avishek.aashayein.dto;

public class MailCheckerTO {

	/* Contains the exact email address requested. */
	private String email;

	/*
	 * Contains a did-you-mean suggestion in case a potential typo has been
	 * detected.
	 */
	private String did_you_mean;

	/*
	 * Returns the local part of the request email address. (e.g. "paul" in
	 * "paul@company.com")
	 */
	private String user;

	/*
	 * Returns the domain of the requested email address. (e.g. "company.com" in
	 * "paul@company.com")
	 */
	private String domain;

	/*
	 * Returns true or false depending on whether or not the general syntax of the
	 * requested email address is valid.
	 */
	private Boolean format_valid;

	/*
	 * Returns true or false depending on whether or not MX-Records for the
	 * requested domain could be found.
	 */
	private Boolean mx_found;

	/*
	 * Returns true or false depending on whether or not the SMTP check of the
	 * requested email address succeeded.
	 */
	private Boolean smtp_check;

	/*
	 * Returns true or false depending on whether or not the requested email address
	 * is found to be part of a catch-all mailbox.
	 */
	private Boolean catch_all;

	/*
	 * Returns true or false depending on whether or not the requested email address
	 * is a role email address. (e.g. "support@company.com",
	 * "postmaster@company.com")
	 */
	private Boolean role;

	/*
	 * Returns true or false depending on whether or not the requested email address
	 * is a disposable email address. (e.g. "user123@mailinator.com")
	 */
	private Boolean disposable;

	/*
	 * Returns true or false depending on whether or not the requested email address
	 * is a free email address. (e.g. "user123@gmail.com", "user123@yahoo.com")
	 */
	private Boolean free;

	/*
	 * Returns a numeric score between 0 and 1 reflecting the quality and
	 * deliverability of the requested email address.
	 */
	private Integer score;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDid_you_mean() {
		return did_you_mean;
	}

	public void setDid_you_mean(String did_you_mean) {
		this.did_you_mean = did_you_mean;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Boolean getFormat_valid() {
		return format_valid;
	}

	public void setFormat_valid(Boolean format_valid) {
		this.format_valid = format_valid;
	}

	public Boolean getMx_found() {
		return mx_found;
	}

	public void setMx_found(Boolean mx_found) {
		this.mx_found = mx_found;
	}

	public Boolean getSmtp_check() {
		return smtp_check;
	}

	public void setSmtp_check(Boolean smtp_check) {
		this.smtp_check = smtp_check;
	}

	public Boolean getCatch_all() {
		return catch_all;
	}

	public void setCatch_all(Boolean catch_all) {
		this.catch_all = catch_all;
	}

	public Boolean getRole() {
		return role;
	}

	public void setRole(Boolean role) {
		this.role = role;
	}

	public Boolean getDisposable() {
		return disposable;
	}

	public void setDisposable(Boolean disposable) {
		this.disposable = disposable;
	}

	public Boolean getFree() {
		return free;
	}

	public void setFree(Boolean free) {
		this.free = free;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "MailCheckerTO [email=" + email + ", did_you_mean=" + did_you_mean + ", user=" + user + ", domain="
				+ domain + ", format_valid=" + format_valid + ", mx_found=" + mx_found + ", smtp_check=" + smtp_check
				+ ", catch_all=" + catch_all + ", role=" + role + ", disposable=" + disposable + ", free=" + free
				+ ", score=" + score + "]";
	}

}
