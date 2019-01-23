package org.avishek.aashayein.command;

import javax.validation.constraints.Pattern;

public class EmployeeTitleCommand {

	@Pattern(regexp = "^$|[123456789]\\d{0,1}", message = "Invalid TitleId for edit")
	private String titleId;

	@Pattern(regexp = "[a-zA-Z]{3,25}", message = "Please enter valid RoleName between 3 to 25 character")
	private String titleName;

	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	@Override
	public String toString() {
		return "EmployeeTitleCommand [titleId=" + titleId + ", titleName=" + titleName + "]";
	}

}
