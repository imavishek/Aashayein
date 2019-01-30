/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.serviceImpl;
 * @FileName EmployeeServiceImpl.java
 * @CreatedDate 29-Jan-2019
 * Modified by @author avishekdas last on 2019-01-29 23:48:01
 */

package org.avishek.aashayein.serviceImpl;

import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.service.EmployeeService;
import org.springframework.transaction.annotation.Transactional;

public class EmployeeServiceImpl implements EmployeeService {

	// Adding Employee Role With Module Permissions
	@Override
	@Transactional
	public boolean addEmployee(EmployeeTO employeeTo) {
		
		// Checking the existence of Email and PhoneNo (Create 2 method)
		
		// Get the next employee code (create a method)
		
		// save profile photo in server rename the file with employeeCode and UUID
		
		// save the employee details with new file name
		return false;
	}

}
