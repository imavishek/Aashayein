package org.avishek.aashayein.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.avishek.aashayein.dao.EmployeeTitleDao;
import org.avishek.aashayein.dto.EmployeeTitleTO;
import org.avishek.aashayein.entities.EmployeeTitle;
import org.avishek.aashayein.utility.DateTime;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeTitleDaoImpl implements EmployeeTitleDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DateTime dateTime;

	@Override
	public List<EmployeeTitleTO> getAllJobTitles() {

		List<EmployeeTitleTO> employeeTitles = null;

		String hql = "FROM EmployeeTitle ORDER BY RecordCreated DESC";
		Query<EmployeeTitle> query = sessionFactory.getCurrentSession().createQuery(hql, EmployeeTitle.class);
		List<EmployeeTitle> employeeTitle = query.list();

		if (!employeeTitle.isEmpty()) {
			employeeTitles = new ArrayList<EmployeeTitleTO>();

			for (EmployeeTitle title : employeeTitle) {
				EmployeeTitleTO employeeTitleTo = new EmployeeTitleTO();

				employeeTitleTo.setTitleId(title.getTitleId());
				employeeTitleTo.setTitleName(title.getTitleName());
				employeeTitleTo.setArchive(title.getArchive());
				employeeTitleTo.setRecordCreated(title.getRecordCreated());
				employeeTitleTo.setRecordUpdated(title.getRecordUpdated());

				employeeTitles.add(employeeTitleTo);
			}
		}

		return employeeTitles;
	}

	// Get Employee Title Details If It's Not Archived
	@Override
	public EmployeeTitleTO getEmployeeTitleById(Integer employeeTitleId) {

		EmployeeTitleTO employeeTitleTo = null;

		String hql = "FROM EmployeeTitle title WHERE title.titleId=?1 AND title.archive=?2";
		Query<EmployeeTitle> query = sessionFactory.getCurrentSession().createQuery(hql, EmployeeTitle.class);
		query.setParameter(1, employeeTitleId);
		query.setParameter(2, (byte) 0);
		EmployeeTitle employeeTitle = query.uniqueResult();

		if (employeeTitle != null) {
			employeeTitleTo = new EmployeeTitleTO();

			employeeTitleTo.setTitleId(employeeTitle.getTitleId());
			employeeTitleTo.setTitleName(employeeTitle.getTitleName());
			employeeTitleTo.setArchive(employeeTitle.getArchive());
			employeeTitleTo.setRecordCreated(employeeTitle.getRecordCreated());
			employeeTitleTo.setRecordUpdated(employeeTitle.getRecordUpdated());
		}

		return employeeTitleTo;
	}

	@Override
	public boolean addEmployeeTitle(EmployeeTitleTO employeeTitleTo) {

		Boolean success = false;

		EmployeeTitle employeeTitle = new EmployeeTitle();
		employeeTitle.setTitleName(employeeTitleTo.getTitleName());
		employeeTitle.setRecordCreated(dateTime.getCurrentDateTime());

		sessionFactory.getCurrentSession().save(employeeTitle);

		success = true;

		return success;
	}

	@Override
	public boolean editEmployeeTitle(EmployeeTitleTO employeeTitleTo) {

		Boolean success = false;

		EmployeeTitle employeeTitle = new EmployeeTitle();
		employeeTitle.setTitleId(employeeTitleTo.getTitleId());
		employeeTitle.setTitleName(employeeTitleTo.getTitleName());
		employeeTitle.setArchive((byte) 0);
		employeeTitle.setRecordUpdated(dateTime.getCurrentDateTime());

		sessionFactory.getCurrentSession().update(employeeTitle);

		success = true;

		return success;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Integer deleteEmployeeTitle(Integer employeeTitleId) {

		Integer noOfRecordUpdated = 0;

		String hql = "UPDATE EmployeeTitle title SET title.archive=?1, title.recordUpdated=?2 WHERE title.titleId=?3 AND title.archive=?4";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(1, (byte) 1);
		query.setParameter(2, dateTime.getCurrentDateTime());
		query.setParameter(3, employeeTitleId);
		query.setParameter(4, (byte) 0);
		noOfRecordUpdated = query.executeUpdate();

		return noOfRecordUpdated;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Integer activeEmployeeTitle(Integer employeeTitleId) {

		Integer noOfRecordUpdated = 0;

		String hql = "UPDATE EmployeeTitle title SET title.archive=?1, title.recordUpdated=?2 WHERE title.titleId=?3";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(1, (byte) 0);
		query.setParameter(2, dateTime.getCurrentDateTime());
		query.setParameter(3, employeeTitleId);
		noOfRecordUpdated = query.executeUpdate();

		return noOfRecordUpdated;
	}

}
