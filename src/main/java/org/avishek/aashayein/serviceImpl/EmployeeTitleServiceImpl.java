package org.avishek.aashayein.serviceImpl;

import java.util.List;

import org.avishek.aashayein.dao.EmployeeTitleDao;
import org.avishek.aashayein.dto.EmployeeTitleTO;
import org.avishek.aashayein.service.EmployeeTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeTitleServiceImpl implements EmployeeTitleService {

	@Autowired
	EmployeeTitleDao employeeTitleDao;

	// Getting list of all job titles
	@Override
	@Transactional
	public List<EmployeeTitleTO> getAllJobTitles() {

		return employeeTitleDao.getAllJobTitles();
	}

	// Getting employee title details by titleId
	@Override
	@Transactional
	public EmployeeTitleTO getEmployeeTitleById(Integer employeeTitleId) {

		return employeeTitleDao.getEmployeeTitleById(employeeTitleId);
	}

	// Adding Employee Title
	@Override
	@Transactional
	public boolean addEmployeeTitle(EmployeeTitleTO employeeTitleTo) {

		return employeeTitleDao.addEmployeeTitle(employeeTitleTo);
	}

	// Editing Employee Title
	@Override
	@Transactional
	public boolean editEmployeeTitle(EmployeeTitleTO employeeTitleTo) {

		return employeeTitleDao.editEmployeeTitle(employeeTitleTo);
	}

	// Deleting Employee Title By Updating Archive to 1
	@Override
	@Transactional
	public Integer deleteEmployeeTitle(Integer employeeTitleId) {

		return employeeTitleDao.deleteEmployeeTitle(employeeTitleId);
	}

	// Active Employee Title By Updating Archive to 0
	@Override
	@Transactional
	public Integer activeEmployeeTitle(Integer employeeTitleId) {

		return employeeTitleDao.activeEmployeeTitle(employeeTitleId);
	}
}
