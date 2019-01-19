package org.avishek.aashayein.daoImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.avishek.aashayein.dao.EmployeeRoleAccessDao;
import org.avishek.aashayein.dto.EmployeeRoleAccessTO;
import org.avishek.aashayein.entities.EmployeeRoleAccess;
import org.avishek.aashayein.entities.RoleId_ModuleId;
import org.avishek.aashayein.utility.CurrentDateTime;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRoleAccessDaoImpl implements EmployeeRoleAccessDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	CurrentDateTime currentDateTime;

	@Override
	public Boolean addModulePermissions(Integer employeeRoleId, EmployeeRoleAccessTO employeeRoleAccessTO) {

		ArrayList<EmployeeRoleAccess> employeeRoleAccessObject = new ArrayList<EmployeeRoleAccess>();

		for (String moduleId : employeeRoleAccessTO.getModuleIds()) {

			EmployeeRoleAccess employeeRoleAccess = new EmployeeRoleAccess();
			RoleId_ModuleId roleId_ModuleId = new RoleId_ModuleId();

			roleId_ModuleId.setRoleId(employeeRoleId);
			roleId_ModuleId.setModuleId(Integer.parseInt(moduleId));
			employeeRoleAccess.setRoleId_ModuleId(roleId_ModuleId);
			employeeRoleAccess.setRecordCreated(currentDateTime.getCurrentDateTime());

			employeeRoleAccessObject.add(employeeRoleAccess);
		}
		for (EmployeeRoleAccess employeeRoleAccess : employeeRoleAccessObject) {
			sessionFactory.getCurrentSession().save(employeeRoleAccess);
		}

		return true;
	}

	@Override
	public EmployeeRoleAccessTO getModuleAccessByRoleId(Integer employeeRoleId) {

		EmployeeRoleAccessTO employeeRoleAccessTo = null;
		List<String> moduleIds = null;

		String hql = "FROM EmployeeRoleAccess roleAccess WHERE roleAccess.roleId_ModuleId.roleId=?1";
		Query<EmployeeRoleAccess> query = sessionFactory.getCurrentSession().createQuery(hql, EmployeeRoleAccess.class);
		query.setParameter(1, employeeRoleId);
		List<EmployeeRoleAccess> roleAccess = query.list();

		if (!roleAccess.isEmpty()) {
			employeeRoleAccessTo = new EmployeeRoleAccessTO();
			moduleIds = new ArrayList<String>();

			for (EmployeeRoleAccess access : roleAccess) {
				moduleIds.add(access.getRoleId_ModuleId().getModuleId().toString());
			}

			employeeRoleAccessTo
					.setModuleIds(Arrays.copyOf(moduleIds.toArray(), moduleIds.toArray().length, String[].class));
		}

		return employeeRoleAccessTo;
	}

}
