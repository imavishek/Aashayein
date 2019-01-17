package org.avishek.aashayein.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.avishek.aashayein.dao.EmployeeModuleDao;
import org.avishek.aashayein.dto.EmployeeModuleTO;
import org.avishek.aashayein.entities.EmployeeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeModuleDaoImpl implements EmployeeModuleDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public List<EmployeeModuleTO> getAllModuless() {

		List<EmployeeModuleTO> employeeModules = null;

		List<EmployeeModule> modules = (List<EmployeeModule>) hibernateTemplate.find("from EmployeeModule");

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
