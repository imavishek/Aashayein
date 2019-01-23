package org.avishek.aashayein.service;

import java.util.List;

import org.avishek.aashayein.dto.EmployeeTitleTO;

public interface EmployeeTitleService {

	List<EmployeeTitleTO> getAllJobTitles();

	boolean addEmployeeTitle(EmployeeTitleTO employeeTitleTo);

	boolean editEmployeeTitle(EmployeeTitleTO employeeTitleTo);

	EmployeeTitleTO getEmployeeTitleById(Integer employeeTitleId);

}
