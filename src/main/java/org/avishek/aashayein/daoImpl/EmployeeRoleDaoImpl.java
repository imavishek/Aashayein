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

	// Get Employee Role Details If It's Not Archived
	@Override
	public EmployeeRoleTO getEmployeeRoleById(Integer employeeRoleId) {

		EmployeeRoleTO employeeRoleTo = null;

		String hql = "FROM EmployeeRole role WHERE role.roleId=?1 AND role.archive=?2";
		Query<EmployeeRole> query = sessionFactory.getCurrentSession().createQuery(hql, EmployeeRole.class);
		query.setParameter(1, employeeRoleId);
		query.setParameter(2, (byte) 0);
		EmployeeRole employeeRole = query.uniqueResult();

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
	@SuppressWarnings("rawtypes")
	public Integer deleteEmployeeRole(Integer employeeRoleId) {

		Integer noOfRecordUpdated = 0;

		String hql = "UPDATE EmployeeRole role SET role.archive=?1, role.recordUpdated=?2 WHERE role.roleId=?3 AND role.archive=?4";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(1, (byte) 1);
		query.setParameter(2, currentDateTime.getCurrentDateTime());
		query.setParameter(3, employeeRoleId);
		query.setParameter(4, (byte) 0);
		noOfRecordUpdated = query.executeUpdate();

		return noOfRecordUpdated;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Integer activeEmployeeRole(Integer employeeRoleId) {

		Integer noOfRecordUpdated = 0;

		String hql = "UPDATE EmployeeRole role SET role.archive=?1, role.recordUpdated=?2 WHERE role.roleId=?3";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(1, (byte) 0);
		query.setParameter(2, currentDateTime.getCurrentDateTime());
		query.setParameter(3, employeeRoleId);
		noOfRecordUpdated = query.executeUpdate();

		return noOfRecordUpdated;
	}

}
