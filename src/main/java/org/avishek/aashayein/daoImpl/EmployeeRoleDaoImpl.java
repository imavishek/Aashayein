package org.avishek.aashayein.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.avishek.aashayein.dao.EmployeeRoleDao;
import org.avishek.aashayein.dto.EmployeeRoleTO;
import org.avishek.aashayein.entities.EmployeeRole;
import org.avishek.aashayein.utility.CurrentDateTime;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRoleDaoImpl implements EmployeeRoleDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	CurrentDateTime currentDateTime;

	@Override
	public Integer addEmployeeRole(EmployeeRoleTO employeeRoleTO) {

		EmployeeRole employeeRole = new EmployeeRole();
		employeeRole.setRoleName(employeeRoleTO.getRoleName());
		employeeRole.setRecordCreated(currentDateTime.getCurrentDateTime());

		sessionFactory.getCurrentSession().save(employeeRole);

		return employeeRole.getRoleId();
	}

	@Override
	public void editEmployeeRole(EmployeeRoleTO employeeRoleTO) {

		EmployeeRole employeeRole = new EmployeeRole();
		employeeRole.setRoleId(employeeRoleTO.getRoleId());
		employeeRole.setRoleName(employeeRoleTO.getRoleName());
		employeeRole.setArchive((byte) 0);
		employeeRole.setRecordUpdated(currentDateTime.getCurrentDateTime());

		sessionFactory.getCurrentSession().update(employeeRole);

	}

	@Override
	public List<EmployeeRoleTO> getAllRoles() {

		List<EmployeeRoleTO> employeeRoles = null;

		String hql = "FROM EmployeeRole ORDER BY RecordCreated DESC";
		Query<EmployeeRole> query = sessionFactory.getCurrentSession().createQuery(hql, EmployeeRole.class);
		List<EmployeeRole> employeeRole = query.list();

		if (!employeeRole.isEmpty()) {
			employeeRoles = new ArrayList<EmployeeRoleTO>();

			for (EmployeeRole role : employeeRole) {
				EmployeeRoleTO employeeRoleTo = new EmployeeRoleTO();

				employeeRoleTo.setRoleId(role.getRoleId());
				employeeRoleTo.setRoleName(role.getRoleName());
				employeeRoleTo.setArchive(role.getArchive());
				employeeRoleTo.setRecordCreated(role.getRecordCreated());
				employeeRoleTo.setRecordUpdated(role.getRecordUpdated());

				employeeRoles.add(employeeRoleTo);
			}
		}

		return employeeRoles;
	}

	@Override
	public EmployeeRoleTO getEmployeeRoleById(Integer employeeRoleId) {

		EmployeeRoleTO employeeRoleTo = null;

		EmployeeRole employeeRole = sessionFactory.getCurrentSession().get(EmployeeRole.class, employeeRoleId);

		if (employeeRole != null) {
			employeeRoleTo = new EmployeeRoleTO();

			employeeRoleTo.setRoleId(employeeRole.getRoleId());
			employeeRoleTo.setRoleName(employeeRole.getRoleName());
			employeeRoleTo.setArchive(employeeRole.getArchive());
			employeeRoleTo.setRecordCreated(employeeRole.getRecordCreated());
			employeeRoleTo.setRecordUpdated(employeeRole.getRecordUpdated());
		}

		return employeeRoleTo;
	}

}
