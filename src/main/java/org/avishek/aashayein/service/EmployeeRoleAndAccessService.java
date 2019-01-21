package org.avishek.aashayein.service;

import java.util.List;

import org.avishek.aashayein.dto.EmployeeModuleTO;
import org.avishek.aashayein.dto.EmployeeRoleAccessTO;
import org.avishek.aashayein.dto.EmployeeRoleTO;

public interface EmployeeRoleAndAccessService {

	public List<EmployeeRoleTO> getAllRoles();

	public List<EmployeeModuleTO> getAllModules();

	public EmployeeRoleTO getEmployeeRoleById(Integer employeeRoleId);

	public EmployeeRoleAccessTO getModuleAccessByRoleId(Integer employeeRoleId);

	public boolean addEmployeeRoleWithModulePermissions(EmployeeRoleTO employeeRoleTO,
			EmployeeRoleAccessTO employeeRoleAccessTO);

	public boolean editEmployeeRoleWithModulePermissions(EmployeeRoleTO employeeRoleTO,
			EmployeeRoleAccessTO employeeRoleAccessTO);

	public Integer deleteEmployeeRole(Integer employeeRoleId);
}
