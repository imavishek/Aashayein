package org.avishek.aashayein.dao;

import java.util.List;

import org.avishek.aashayein.dto.EmployeeTitleTO;

public interface EmployeeTitleDao {

	List<EmployeeTitleTO> getAllJobTitles();

	EmployeeTitleTO getEmployeeTitleById(Integer employeeTitleId);

	boolean addEmployeeTitle(EmployeeTitleTO employeeTitleTo);

	boolean editEmployeeTitle(EmployeeTitleTO employeeTitleTo);

	Integer deleteEmployeeTitle(Integer employeeTitleId);

	Integer activeEmployeeTitle(Integer employeeTitleId);

}
