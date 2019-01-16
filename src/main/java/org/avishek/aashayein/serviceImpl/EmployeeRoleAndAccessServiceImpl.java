package org.avishek.aashayein.serviceImpl;

import org.avishek.aashayein.dao.EmployeeRoleAccessDao;
import org.avishek.aashayein.dao.EmployeeRoleDao;
import org.avishek.aashayein.dto.EmployeeRoleAccessTO;
import org.avishek.aashayein.dto.EmployeeRoleTO;
import org.avishek.aashayein.service.EmployeeRoleAndAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeRoleAndAccessServiceImpl implements EmployeeRoleAndAccessService {

	@Autowired
	EmployeeRoleDao employeeRoleDao;

	@Autowired
	EmployeeRoleAccessDao employeeRoleAccessDao;

	// Adding Employee Role With Module Permissions
	@Override
	public boolean addEmployeeRoleWithModulePermissions(EmployeeRoleTO employeeRoleTO, EmployeeRoleAccessTO employeeRoleAccessTO) {


		Integer employeeRoleId = employeeRoleDao.addEmployeeRole(employeeRoleTO);
		return false;
	}

}
