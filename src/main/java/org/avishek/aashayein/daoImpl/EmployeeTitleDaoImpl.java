package org.avishek.aashayein.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.avishek.aashayein.dao.EmployeeTitleDao;
import org.avishek.aashayein.dto.EmployeeTitleTO;
import org.avishek.aashayein.entities.EmployeeTitle;
import org.avishek.aashayein.utility.CurrentDateTime;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeTitleDaoImpl implements EmployeeTitleDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	CurrentDateTime currentDateTime;

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

}
