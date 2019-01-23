/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.exception;
 * @FileName EmployeeTitleNotFoundException.java
 * @CreatedDate 24-Jan-2019
 * Modified by @author avishekdas last on 2019-01-24 01:30:09
 */

package org.avishek.aashayein.exception;

public class EmployeeTitleNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	public EmployeeTitleNotFoundException() {
		this("Employee Title Not Found");
	}

	public EmployeeTitleNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
