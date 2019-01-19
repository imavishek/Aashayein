package org.avishek.aashayein.dao;

import org.avishek.aashayein.dto.EmployeeRoleAccessTO;

public interface EmployeeRoleAccessDao {

	public Boolean addModulePermissions(Integer employeeRoleId, EmployeeRoleAccessTO employeeRoleAccessTO);

	EmployeeRoleAccessTO getModuleAccessByRoleId(Integer employeeRoleId);

}
