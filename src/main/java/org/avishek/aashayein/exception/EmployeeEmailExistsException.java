package org.avishek.aashayein.exception;

public class EmployeeEmailExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	public EmployeeEmailExistsException() {
		this("Employee Email Exists");
	}

	public EmployeeEmailExistsException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
