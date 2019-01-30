/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.exception;
 * @FileName EmployeeNotFoundException.java
 * @CreatedDate 29-Jan-2019
 * Modified by @author avishekdas last on 2019-01-29 23:14:27
 */

package org.avishek.aashayein.exception;


public class EmployeeNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	public EmployeeNotFoundException() {
		this("Employee Not Found");
	}

	public EmployeeNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
