package org.avishek.aashayein.exception;

public class UploadingFailedException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	public UploadingFailedException() {
		this("Failed to upload file");
	}

	public UploadingFailedException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
