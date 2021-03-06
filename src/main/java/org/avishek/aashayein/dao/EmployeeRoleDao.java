package org.avishek.aashayein.dao;

import java.util.List;

import org.avishek.aashayein.dto.EmployeeRoleTO;

public interface EmployeeRoleDao {

	public Integer addEmployeeRole(EmployeeRoleTO employeeRoleTO);

	public List<EmployeeRoleTO> getAllRoles();

	public EmployeeRoleTO getEmployeeRoleById(Integer employeeRoleId);

	public void editEmployeeRole(EmployeeRoleTO employeeRoleTO);

	public Integer deleteEmployeeRole(Integer employeeRoleId);

	public Integer activeEmployeeRole(Integer employeeRoleId);

}
