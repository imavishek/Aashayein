package org.avishek.aashayein.service;

import java.util.List;

import org.avishek.aashayein.dto.EmployeeTitleTO;

public interface EmployeeTitleService {

	List<EmployeeTitleTO> getAllJobTitles();

	EmployeeTitleTO getEmployeeTitleById(Integer employeeTitleId);

	boolean addEmployeeTitle(EmployeeTitleTO employeeTitleTo);

	boolean editEmployeeTitle(EmployeeTitleTO employeeTitleTo);

	Integer deleteEmployeeTitle(Integer employeeTitleId);

	Integer activeEmployeeTitle(Integer employeeTitleId);

}
