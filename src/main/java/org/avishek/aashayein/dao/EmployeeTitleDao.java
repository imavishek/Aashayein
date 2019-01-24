package org.avishek.aashayein.dao;

import java.util.List;

import org.avishek.aashayein.dto.EmployeeTitleTO;

public interface EmployeeTitleDao {

	List<EmployeeTitleTO> getAllJobTitles();

	boolean addEmployeeTitle(EmployeeTitleTO employeeTitleTo);

	boolean editEmployeeTitle(EmployeeTitleTO employeeTitleTo);

	EmployeeTitleTO getEmployeeTitleById(Integer employeeTitleId);

}
