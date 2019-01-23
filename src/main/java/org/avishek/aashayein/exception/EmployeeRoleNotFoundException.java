/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.exception;
 * @FileName EmployeeRoleNotFoundException.java
 * @CreatedDate 19-Jan-2019
 * Modified by @author avishekdas last on 2019-01-19 15:09:19
 */

package org.avishek.aashayein.exception;

public class EmployeeRoleNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	public EmployeeRoleNotFoundException() {
		this("Employee Role Not Found");
	}

	public EmployeeRoleNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
