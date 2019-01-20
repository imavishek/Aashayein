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
	@Transactional
	public boolean addEmployeeRoleWithModulePermissions(EmployeeRoleTO employeeRoleTO,
			EmployeeRoleAccessTO employeeRoleAccessTO) {

		Boolean success = false;

		Integer employeeRoleId = employeeRoleDao.addEmployeeRole(employeeRoleTO);

		success = employeeRoleAccessDao.addModulePermissions(employeeRoleId, employeeRoleAccessTO);

		return success;
	}

	// Editing Employee Role With Module Permissions
	@Override
	@Transactional
	public boolean editEmployeeRoleWithModulePermissions(EmployeeRoleTO employeeRoleTO,
			EmployeeRoleAccessTO employeeRoleAccessTO) {

		Boolean success = false;

		employeeRoleDao.editEmployeeRole(employeeRoleTO);

		success = employeeRoleAccessDao.editModulePermissions(employeeRoleTO.getRoleId(), employeeRoleAccessTO);

		return success;
	}

	// Getting list of all employee roles
	@Override
	@Transactional
	public List<EmployeeRoleTO> getAllRoles() {

		return employeeRoleDao.getAllRoles();
	}

	// Getting list of all Modules
	@Override
	@Transactional
	public List<EmployeeModuleTO> getAllModules() {

		return employeeModuleDao.getAllModules();
	}

	// Getting employee role details by roldId
	@Override
	@Transactional
	public EmployeeRoleTO getEmployeeRoleById(Integer employeeRoleId) {

		return employeeRoleDao.getEmployeeRoleById(employeeRoleId);
	}

	// Getting modules accessed by roleId
	@Override
	@Transactional
	public EmployeeRoleAccessTO getModuleAccessByRoleId(Integer employeeRoleId) {

		return employeeRoleAccessDao.getModuleAccessByRoleId(employeeRoleId);
	}

}
