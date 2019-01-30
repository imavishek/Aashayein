/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.service;
 * @FileName EmployeeService.java
 * @CreatedDate 29-Jan-2019
 * Modified by @author avishekdas last on 2019-01-29 23:47:43
 */

package org.avishek.aashayein.service;

import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.exception.EmployeeEmailExistsException;
import org.avishek.aashayein.exception.EmployeeMobileNumberExistsException;

public interface EmployeeService {

	String getNextEmployeeCode();

	boolean addEmployee(EmployeeTO employeeTo) throws EmployeeEmailExistsException, EmployeeMobileNumberExistsException;

	boolean emailExist(String email);

	boolean mobileNumberExist(String mobileNumber);

}
