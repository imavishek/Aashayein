package org.avishek.aashayein.serviceImpl;

import org.avishek.aashayein.dao.EmployeeDao;
import org.avishek.aashayein.dto.EmployeeDetails;
import org.avishek.aashayein.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements UserDetailsService {

	@Autowired
	private EmployeeDao employeeDao;

	// Loading User By Username
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeDao.getEmployeeByUsername(username);

		if (employee == null) {
			throw new UsernameNotFoundException("Username Not Found");
		}

		return new EmployeeDetails(employee);
	}

}
