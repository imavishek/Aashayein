package org.avishek.aashayein.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.avishek.aashayein.dao.EmployeeModuleDao;
import org.avishek.aashayein.dto.EmployeeModuleTO;
import org.avishek.aashayein.entities.EmployeeModule;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeModuleDaoImpl implements EmployeeModuleDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<EmployeeModuleTO> getAllModules() {

		List<EmployeeModuleTO> employeeModules = null;

		String hql = "FROM EmployeeModule ORDER BY ModuleName ASC";
		Query<EmployeeModule> query = sessionFactory.getCurrentSession().createQuery(hql, EmployeeModule.class);
		List<EmployeeModule> modules = query.list();

		if (!modules.isEmpty()) {
			employeeModules = new ArrayList<EmployeeModuleTO>();

			for (EmployeeModule module : modules) {
				EmployeeModuleTO employeeModuleTO = new EmployeeModuleTO();

				employeeModuleTO.setModuleId(module.getModuleId());
				employeeModuleTO.setModuleName(module.getModuleName());

				employeeModules.add(employeeModuleTO);

			}

		}

		return employeeModules;
	}

}
