package org.avishek.aashayein.dao;

import java.util.List;

import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.entities.Employee;

public interface EmployeeDao {

	List<EmployeeTO> getAllEmployees();

	EmployeeTO getEmployeeDetailsById(Integer employeeId);

	EmployeeTO getEmployeeByEmail(String email);

	EmployeeTO getEmployeeByMobileNumber(String mobileNumber);

	String getLastEmployeeId();

	Employee addEmployee(EmployeeTO employeeTo);

}
