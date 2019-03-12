package org.avishek.aashayein.dao;

import java.util.List;

import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.entities.Employee;

public interface EmployeeDao {

	List<EmployeeTO> getAllEmployees();

	EmployeeTO getEmployeeDetailsById(Integer employeeId);

	EmployeeTO getEmployeeByEmail(String email);

	EmployeeTO getEmployeeByMobileNumber(String mobileNumber);

	EmployeeTO getEmployeeByToken(String token);

	Employee getEmployeeByUsername(String username);

	String getLastEmployeeId();

	Employee addEmployee(EmployeeTO employeeTo);

	String editEmployee(EmployeeTO employeeTo);

	String editEmployeeProfile(EmployeeTO employeeTo);

	Integer archiveEmployee(Integer employeeId);

	Integer unArchiveEmployee(Integer employeeId);

	Integer savePassword(EmployeeTO employeeTo);

	Integer activeEmployee(Integer employeeId);

	Employee updateTokenUUID(Integer employeeId);
}
