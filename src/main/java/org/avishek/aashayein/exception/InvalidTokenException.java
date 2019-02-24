package org.avishek.aashayein.exception;

public class InvalidTokenException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	public InvalidTokenException() {
		this("Invalid Token");
	}

	public InvalidTokenException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
