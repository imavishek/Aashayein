package org.avishek.aashayein.daoImpl;

import java.util.ArrayList;

import org.avishek.aashayein.dao.EmployeeRoleAccessDao;
import org.avishek.aashayein.dto.EmployeeRoleAccessTO;
import org.avishek.aashayein.entities.EmployeeRoleAccess;
import org.avishek.aashayein.entities.RoleId_ModuleId;
import org.avishek.aashayein.utility.CurrentDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRoleAccessDaoImpl implements EmployeeRoleAccessDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	CurrentDateTime currentDateTime;

	@Override
	public Boolean addModulePermissions(Integer employeeRoleId, EmployeeRoleAccessTO employeeRoleAccessTO) {

		ArrayList<EmployeeRoleAccess> employeeRoleAccessObject = new ArrayList<EmployeeRoleAccess>();

		for (Integer moduleId : employeeRoleAccessTO.getModuleIds()) {

			EmployeeRoleAccess employeeRoleAccess = new EmployeeRoleAccess();
			RoleId_ModuleId roleId_ModuleId = new RoleId_ModuleId();

			roleId_ModuleId.setRoleId(employeeRoleId);
			roleId_ModuleId.setModuleId(moduleId);
			employeeRoleAccess.setRoleId_ModuleId(roleId_ModuleId);
			employeeRoleAccess.setRecordCreated(currentDateTime.getCurrentDateTime());

			employeeRoleAccessObject.add(employeeRoleAccess);
		}
		for (EmployeeRoleAccess employeeRoleAccess : employeeRoleAccessObject) {
			hibernateTemplate.save(employeeRoleAccess);
		}

		return true;
	}

}
