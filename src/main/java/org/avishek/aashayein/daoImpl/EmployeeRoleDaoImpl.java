package org.avishek.aashayein.daoImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

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

}
