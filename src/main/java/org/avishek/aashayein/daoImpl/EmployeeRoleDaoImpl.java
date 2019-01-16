package org.avishek.aashayein.daoImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.avishek.aashayein.dao.EmployeeRoleDao;
import org.avishek.aashayein.dto.EmployeeRoleTO;
import org.avishek.aashayein.entities.EmployeeRole;
import org.avishek.aashayein.utility.CurrentDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRoleDaoImpl implements EmployeeRoleDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	CurrentDateTime currentDateTime;

	@Override
	public Integer addEmployeeRole(EmployeeRoleTO employeeRoleTO) {

		EmployeeRole employeeRole = new EmployeeRole();
		employeeRole.setRoleName(employeeRoleTO.getRoleName());
		employeeRole.setArchive((byte) 0);
		employeeRole.setRecordCreated(currentDateTime.getCurrentDateTime());

		hibernateTemplate.save(employeeRole);

		return employeeRole.getRoleId();
	}

	@Override
	public List<EmployeeRoleTO> getAllRoles() {

		List<EmployeeRoleTO> employeeRoles = null;

		List<EmployeeRole> roles = (List<EmployeeRole>) hibernateTemplate.find("from EmployeeRole");

		if (!roles.isEmpty()) {
			employeeRoles = new ArrayList<EmployeeRoleTO>();

			for (EmployeeRole role : roles) {
				EmployeeRoleTO employeeRoleTO = new EmployeeRoleTO();

				employeeRoleTO.setRoleId(role.getRoleId());
				employeeRoleTO.setRoleName(role.getRoleName());

				employeeRoles.add(employeeRoleTO);

			}

		}

		return employeeRoles;
	}

}
