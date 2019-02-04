package org.avishek.aashayein.dao;

import org.avishek.aashayein.dto.EmployeeTO;

public interface EmployeeDao {

	EmployeeTO getEmployeeByEmail(String email);

	EmployeeTO getEmployeeByMobileNumber(String mobileNumber);

	String getLastEmployeeId();

	boolean addEmployee(EmployeeTO employeeTo);

}
