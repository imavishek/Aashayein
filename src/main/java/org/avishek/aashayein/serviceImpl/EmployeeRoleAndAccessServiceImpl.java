package org.avishek.aashayein.serviceImpl;

import java.util.List;

import org.avishek.aashayein.dao.EmployeeModuleDao;
import org.avishek.aashayein.dao.EmployeeRoleAccessDao;
import org.avishek.aashayein.dao.EmployeeRoleDao;
import org.avishek.aashayein.dto.EmployeeModuleTO;
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

	@Autowired
	EmployeeModuleDao employeeModuleDao;

	// Adding Employee Role With Module Permissions
	@Override
	public boolean addEmployeeRoleWithModulePermissions(EmployeeRoleTO employeeRoleTO,
			EmployeeRoleAccessTO employeeRoleAccessTO) {

		Integer employeeRoleId = employeeRoleDao.addEmployeeRole(employeeRoleTO);

		Boolean success = employeeRoleAccessDao.addModulePermissions(employeeRoleId, employeeRoleAccessTO);

		return success;
	}

	@Override
	public List<EmployeeRoleTO> getAllRoles() {

		return employeeRoleDao.getAllRoles();
	}

	@Override
	public List<EmployeeModuleTO> getAllModuless() {

		return employeeModuleDao.getAllModuless();
	}

}
