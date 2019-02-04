package org.avishek.aashayein.dao;

import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.entities.Employee;

public interface EmployeeDao {

	EmployeeTO getEmployeeByEmail(String email);

	EmployeeTO getEmployeeByMobileNumber(String mobileNumber);

	String getLastEmployeeId();

	Employee addEmployee(EmployeeTO employeeTo);

}
