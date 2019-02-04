/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.daoImpl;
 * @FileName EmployeeDaoImpl.java
 * @CreatedDate 28-Jan-2019
 * Modified by @author avishekdas last on 2019-01-28 21:55:00
 */

package org.avishek.aashayein.daoImpl;

import org.avishek.aashayein.dao.EmployeeDao;
import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.entities.Employee;
import org.avishek.aashayein.entities.EmployeeRole;
import org.avishek.aashayein.entities.EmployeeTitle;
import org.avishek.aashayein.utility.CurrentDateTime;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	CurrentDateTime currentDateTime;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public EmployeeTO getEmployeeByEmail(String email) {

		EmployeeTO employeeTo = null;

		String hql = "FROM Employee employee WHERE employee.email=?1";
		Query<Employee> query = sessionFactory.getCurrentSession().createQuery(hql, Employee.class);
		query.setParameter(1, email);
		Employee employee = query.uniqueResult();

		if (employee != null) {
			employeeTo = new EmployeeTO();

			employeeTo.setFirstName(employee.getFirstName());
		}

		return employeeTo;
	}

	@Override
	public EmployeeTO getEmployeeByMobileNumber(String mobileNumber) {

		EmployeeTO employeeTo = null;

		String hql = "FROM Employee employee WHERE employee.mobileNumber=?1";
		Query<Employee> query = sessionFactory.getCurrentSession().createQuery(hql, Employee.class);
		query.setParameter(1, mobileNumber);
		Employee employee = query.uniqueResult();

		if (employee != null) {
			employeeTo = new EmployeeTO();

			employeeTo.setFirstName(employee.getFirstName());
		}

		return employeeTo;
	}

	@Override
	public String getLastEmployeeId() {

		String lastEmployeeId = null;

		String hql = "FROM Employee employee ORDER BY employee.employeeId_Code.employeeId DESC";
		Query<Employee> query = sessionFactory.getCurrentSession().createQuery(hql, Employee.class);
		query.setMaxResults(1);
		Employee employee = query.uniqueResult();

		if (employee != null) {
			lastEmployeeId = employee.getEmployeeId_Code().getEmployeeId().toString();
		}

		return lastEmployeeId;
	}

	@Override
	public boolean addEmployee(EmployeeTO employeeTo) {

		Boolean success = false;

		EmployeeTitle employeeTitle = new EmployeeTitle();
		employeeTitle.setTitleId(employeeTo.getJobTitleId());

		EmployeeRole employeeRole = new EmployeeRole();
		employeeRole.setRoleId(employeeTo.getRoleId());

		Employee employee = new Employee();
		//employee.setEmployeeCode(employeeTo.getEmployeeCode());
		employee.setFirstName(employeeTo.getFirstName());
		employee.setMiddleName(employeeTo.getMiddleName().isEmpty() ? null : employeeTo.getMiddleName());
		employee.setLastName(employeeTo.getLastName());
		employee.setGender(employeeTo.getGender());
		employee.setMobileNumber(employeeTo.getMobileNumber());
		employee.setAlternateMobileNumber(
				employeeTo.getAlternateMobileNumber().isEmpty() ? null : employeeTo.getAlternateMobileNumber());
		employee.setEmail(employeeTo.getEmail());
		employee.setAlternateEmail(employeeTo.getAlternateEmail().isEmpty() ? null : employeeTo.getAlternateEmail());

		// By default the password is "aashayein"
		employee.setPassword(passwordEncoder.encode("aashayein"));

		employee.setTitle(employeeTitle);
		employee.setRole(employeeRole);
		employee.setJoiningDate(employeeTo.getJoiningDate());
		employee.setProfilePhoto(employeeTo.getProfilePhoto());
		employee.setRecordCreated(currentDateTime.getCurrentDateTime());

		sessionFactory.getCurrentSession().save(employee);

		success = true;

		return success;
	}

}
