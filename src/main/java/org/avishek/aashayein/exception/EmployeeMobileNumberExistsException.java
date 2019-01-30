package org.avishek.aashayein.exception;

public class EmployeeMobileNumberExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	public EmployeeMobileNumberExistsException() {
		this("Employee MobileNumber Exists");
	}

	public EmployeeMobileNumberExistsException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
