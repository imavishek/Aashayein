package org.avishek.aashayein.command;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

public class EditEmployeeCommand {

	@Pattern(regexp = "^$|[123456789]\\d{0,1}", message = "Invalid EmployeeId for edit")
	private String employeeId;

	// Conseder employee code format as "asha-00001"
	@Pattern(regexp = "(asha-)\\d{4}[123456789]", message = "Invalid EmployeeCode for edit")
	private String employeeCode;

	@NotNull(message = "Please select Title")
	private Integer title;

	@NotNull(message = "Please select Role")
	private Integer role;

	@NotNull(message = "Please enter Joining Date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@FutureOrPresent(message = "Enter valid Joining Date")
	private Date joiningDate;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
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

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Override
	public String toString() {
		return "EditEmployeeCommand [employeeId=" + employeeId + ", title=" + title + ", role=" + role + "]";
	}

}
