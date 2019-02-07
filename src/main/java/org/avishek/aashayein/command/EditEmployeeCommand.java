package org.avishek.aashayein.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EditEmployeeCommand {

	@Pattern(regexp = "^$|[123456789]\\d{0,1}", message = "Invalid RoleId for edit")
	private String employeeId;

	@NotNull(message = "Please select Title")
	private Integer title;

	@NotNull(message = "Please select Role")
	private Integer role;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getTitle() {
		return title;
	}

	public void setTitle(Integer title) {
		this.title = title;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "EditEmployeeCommand [employeeId=" + employeeId + ", title=" + title + ", role=" + role + "]";
	}

}
