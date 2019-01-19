package org.avishek.aashayein.service;

import java.util.List;

import org.avishek.aashayein.dto.EmployeeModuleTO;
import org.avishek.aashayein.dto.EmployeeRoleAccessTO;
import org.avishek.aashayein.dto.EmployeeRoleTO;

public interface EmployeeRoleAndAccessService {

	public boolean addEmployeeRoleWithModulePermissions(EmployeeRoleTO employeeRoleTO,
			EmployeeRoleAccessTO employeeRoleAccessTO);

	public List<EmployeeRoleTO> getAllRoles();

	public List<EmployeeModuleTO> getAllModuless();

	public EmployeeRoleTO getEmployeeRoleById(Integer employeeRoleId);

	public EmployeeRoleAccessTO getModuleAccessByRoleId(Integer employeeRoleId);
}
