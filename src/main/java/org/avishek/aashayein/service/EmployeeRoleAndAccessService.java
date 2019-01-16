package org.avishek.aashayein.service;

import java.util.List;

import org.avishek.aashayein.dto.EmployeeRoleAccessTO;
import org.avishek.aashayein.dto.EmployeeRoleTO;

public interface EmployeeRoleAndAccessService {

	public boolean addEmployeeRoleWithModulePermissions(EmployeeRoleTO employeeRoleTO,
			EmployeeRoleAccessTO employeeRoleAccessTO);
	
	public List<EmployeeRoleTO> getAllRoles();
}
