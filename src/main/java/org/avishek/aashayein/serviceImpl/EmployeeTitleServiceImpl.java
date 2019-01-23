package org.avishek.aashayein.serviceImpl;

import java.util.List;

import org.avishek.aashayein.dao.EmployeeTitleDao;
import org.avishek.aashayein.dto.EmployeeTitleTO;
import org.avishek.aashayein.service.EmployeeTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeTitleServiceImpl implements EmployeeTitleService {

	@Autowired
	EmployeeTitleDao employeeTitleDao;

	// Getting list of all job titles
	@Override
	@Transactional
	public List<EmployeeTitleTO> getAllJobTitles() {

		return employeeTitleDao.getAllJobTitles();
	}

}
