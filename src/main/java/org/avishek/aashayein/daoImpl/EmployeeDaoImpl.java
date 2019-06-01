/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.daoImpl;
 * @FileName EmployeeDaoImpl.java
 * @CreatedDate 28-Jan-2019
 * Modified by @author avishekdas last on 2019-01-28 21:55:00
 */

package org.avishek.aashayein.daoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.avishek.aashayein.dao.EmployeeDao;
import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.entities.City;
import org.avishek.aashayein.entities.Country;
import org.avishek.aashayein.entities.Employee;
import org.avishek.aashayein.entities.EmployeeAddress;
import org.avishek.aashayein.entities.EmployeeRole;
import org.avishek.aashayein.entities.EmployeeTitle;
import org.avishek.aashayein.entities.State;
import org.avishek.aashayein.utility.DateTime;
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
	DateTime dateTime;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<EmployeeTO> getAllEmployees() {

		List<EmployeeTO> employees = new ArrayList<EmployeeTO>();

		String hql = "FROM Employee ORDER BY RecordCreated DESC";
		Query<Employee> query = sessionFactory.getCurrentSession().createQuery(hql, Employee.class);
		List<Employee> employee = query.list();

		if (!employee.isEmpty()) {

			for (Employee emp : employee) {
				EmployeeTO employeeTo = new EmployeeTO();

				employeeTo.setEmployeeId(emp.getEmployeeId());
				employeeTo.setEmployeeCode(emp.getEmployeeCode());
				employeeTo.setFirstName(emp.getFirstName());
				employeeTo.setMiddleName(emp.getMiddleName());
				employeeTo.setLastName(emp.getLastName());
				employeeTo.setFullName(emp.getFullName());
				employeeTo.setGender(emp.getGender());
				employeeTo.setMobileNumber(emp.getMobileNumber());
				employeeTo.setAlternateMobileNumber(emp.getAlternateMobileNumber());
				employeeTo.setEmail(emp.getEmail());
				employeeTo.setAlternateEmail(emp.getAlternateEmail());
				employeeTo.setJobTitleName(emp.getTitle().getTitleName());
				employeeTo.setRoleName(emp.getRole().getRoleName());
				if(emp.getAddress() != null) {
					employeeTo.setCountryName(emp.getAddress().getCountry().getCountryName());
					employeeTo.setStateName(emp.getAddress().getState().getStateName());
					employeeTo.setCityName(emp.getAddress().getCity().getCityName());
					employeeTo.setPostalCode(emp.getAddress().getPostalCode());
					employeeTo.setAddressLine1(emp.getAddress().getAddressLine1());
				}
				employeeTo.setActive(emp.getActive());
				employeeTo.setArchive(emp.getArchive());
				employeeTo.setProfilePhoto(emp.getProfilePhoto());
				employeeTo.setJoiningDate(emp.getJoiningDate());
				employeeTo.setRecordCreated(emp.getRecordCreated());
				employeeTo.setRecordUpdated(emp.getRecordUpdated());

				employees.add(employeeTo);
			}
		}

		return employees;
	}

	// Get Employee Details If It's Not Archived
	@Override
	public EmployeeTO getEmployeeDetailsById(Integer employeeId) {

		EmployeeTO employeeTo = null;

		String hql = "FROM Employee employee WHERE employee.employeeId=?1 AND employee.archive=?2";
		Query<Employee> query = sessionFactory.getCurrentSession().createQuery(hql, Employee.class);
		query.setParameter(1, employeeId);
		query.setParameter(2, (byte) 0);
		Employee employee = query.uniqueResult();

		if (employee != null) {
			employeeTo = new EmployeeTO();

			employeeTo.setEmployeeId(employee.getEmployeeId());
			employeeTo.setEmployeeCode(employee.getEmployeeCode());
			employeeTo.setFirstName(employee.getFirstName());
			employeeTo.setMiddleName(employee.getMiddleName());
			employeeTo.setLastName(employee.getLastName());
			employeeTo.setFullName(employee.getFullName());
			employeeTo.setGender(employee.getGender());
			employeeTo.setMobileNumber(employee.getMobileNumber());
			employeeTo.setAlternateMobileNumber(employee.getAlternateMobileNumber());
			employeeTo.setEmail(employee.getEmail());
			employeeTo.setAlternateEmail(employee.getAlternateEmail());

			// If address is available then set address details
			if (employee.getAddress() != null) {
				employeeTo.setAddressId(employee.getAddress().getAddressId());
				employeeTo.setCountryId(employee.getAddress().getCountry().getCountryId());
				employeeTo.setCountryName(employee.getAddress().getCountry().getCountryName());
				employeeTo.setStateId(employee.getAddress().getState().getStateId());
				employeeTo.setStateName(employee.getAddress().getState().getStateName());
				employeeTo.setCityId(employee.getAddress().getCity().getCityId());
				employeeTo.setCityName(employee.getAddress().getCity().getCityName());
				employeeTo.setPostalCode(employee.getAddress().getPostalCode());
				employeeTo.setAddressLine1(employee.getAddress().getAddressLine1());
				employeeTo.setAddressLine2(employee.getAddress().getAddressLine2());
			}

			employeeTo.setJobTitleId(employee.getTitle().getTitleId());
			employeeTo.setJobTitleName(employee.getTitle().getTitleName());
			employeeTo.setRoleId(employee.getRole().getRoleId());
			employeeTo.setRoleName(employee.getRole().getRoleName());
			employeeTo.setActive(employee.getActive());
			employeeTo.setArchive(employee.getArchive());
			employeeTo.setProfilePhoto(employee.getProfilePhoto());
			employeeTo.setJoiningDate(employee.getJoiningDate());
			employeeTo.setRecordCreated(employee.getRecordCreated());
			employeeTo.setRecordUpdated(employee.getRecordUpdated());
		}

		return employeeTo;
	}

	@Override
	public EmployeeTO getEmployeeByEmail(String email) {

		EmployeeTO employeeTo = null;

		String hql = "FROM Employee employee WHERE employee.email=?1";
		Query<Employee> query = sessionFactory.getCurrentSession().createQuery(hql, Employee.class);
		query.setParameter(1, email);
		Employee employee = query.uniqueResult();

		if (employee != null) {
			employeeTo = new EmployeeTO();

			employeeTo.setEmployeeId(employee.getEmployeeId());
			employeeTo.setFirstName(employee.getFirstName());
			employeeTo.setActive(employee.getActive());
			employeeTo.setArchive(employee.getArchive());
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
	public EmployeeTO getEmployeeByToken(String token) {

		EmployeeTO employeeTo = null;

		String hql = "FROM Employee employee WHERE employee.tokenUUID=?1 AND employee.archive=?2";
		Query<Employee> query = sessionFactory.getCurrentSession().createQuery(hql, Employee.class);
		query.setParameter(1, token);
		query.setParameter(2, (byte) 0);
		Employee employee = query.uniqueResult();

		if (employee != null) {
			employeeTo = new EmployeeTO();

			employeeTo.setEmployeeId(employee.getEmployeeId());
			employeeTo.setEmployeeCode(employee.getEmployeeCode());
			employeeTo.setTokenUUID(employee.getTokenUUID());
			employeeTo.setTokenGeneratedDate(employee.getTokenGeneratedDate());
		}

		return employeeTo;
	}

	@Override
	public Employee getEmployeeByUsername(String username) {

		Employee employee = null;

		String hql = "FROM Employee employee WHERE employee.email=?1 AND employee.archive=?2 AND employee.active=?3";
		Query<Employee> query = sessionFactory.getCurrentSession().createQuery(hql, Employee.class);
		query.setParameter(1, username);
		query.setParameter(2, (byte) 0);
		query.setParameter(3, (byte) 1);
		employee = query.uniqueResult();

		return employee;
	}

	@Override
	public String getLastEmployeeId() {

		String lastEmployeeId = null;

		String hql = "FROM Employee employee ORDER BY employee.employeeId DESC";
		Query<Employee> query = sessionFactory.getCurrentSession().createQuery(hql, Employee.class);
		query.setMaxResults(1);
		Employee employee = query.uniqueResult();

		if (employee != null) {
			lastEmployeeId = employee.getEmployeeId().toString();
		}

		return lastEmployeeId;
	}

	@Override
	public Employee addEmployee(EmployeeTO employeeTo) {

		Employee employee = null;

		EmployeeTitle employeeTitle = new EmployeeTitle();
		employeeTitle.setTitleId(employeeTo.getJobTitleId());

		EmployeeRole employeeRole = new EmployeeRole();
		employeeRole.setRoleId(employeeTo.getRoleId());

		employee = new Employee();
		employee.setEmployeeCode(employeeTo.getEmployeeCode());
		employee.setFirstName(employeeTo.getFirstName());
		employee.setMiddleName(employeeTo.getMiddleName().isEmpty() ? null : employeeTo.getMiddleName());
		employee.setLastName(employeeTo.getLastName());
		employee.setGender(employeeTo.getGender());
		employee.setMobileNumber(employeeTo.getMobileNumber());
		employee.setAlternateMobileNumber(
				employeeTo.getAlternateMobileNumber().isEmpty() ? null : employeeTo.getAlternateMobileNumber());
		employee.setEmail(employeeTo.getEmail());
		employee.setAlternateEmail(employeeTo.getAlternateEmail().isEmpty() ? null : employeeTo.getAlternateEmail());
		employee.setTitle(employeeTitle);
		employee.setRole(employeeRole);
		employee.setProfilePhoto(employeeTo.getProfilePhoto());
		employee.setTokenUUID(UUID.randomUUID().toString());
		employee.setTokenGeneratedDate(dateTime.getCurrentDateTime());
		employee.setJoiningDate(employeeTo.getJoiningDate());
		employee.setRecordCreated(dateTime.getCurrentDateTime());

		sessionFactory.getCurrentSession().save(employee);

		return employee;
	}

	@Override
	public String editEmployee(EmployeeTO employeeTo) {

		String message = null;

		String hql = "UPDATE Employee employee SET employee.title.titleId = :titleId, employee.role.roleId = :roleId, employee.joiningDate = :joiningDate, employee.recordUpdated = :recordUpdated WHERE employee.employeeId = :employeeId";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("titleId", employeeTo.getJobTitleId());
		query.setParameter("roleId", employeeTo.getRoleId());
		query.setParameter("joiningDate", employeeTo.getJoiningDate());
		query.setParameter("employeeId", employeeTo.getEmployeeId());
		query.setParameter("recordUpdated", dateTime.getCurrentDateTime());
		int res = query.executeUpdate();

		if (res == 0) {
			message = "Failed to edit employee";
		}

		return message;
	}

	@Override
	public String editEmployeeProfile(EmployeeTO employeeTo) {

		String message = null;
		Employee employee = null;
		EmployeeAddress address = null;

		// Getting the employee profile details
		employee = (Employee) sessionFactory.getCurrentSession().get(Employee.class, employeeTo.getEmployeeId());

		if (employee == null) {
			message = "Employee Not Found";

			return message;
		}

		// Setting the Country entity value
		Country country = new Country();
		country.setCountryId(employeeTo.getCountryId());

		// Setting the State entity value
		State state = new State();
		state.setStateId(employeeTo.getStateId());

		// Setting the City entity value
		City city = new City();
		city.setCityId(employeeTo.getCityId());

		/*
		 * If address not available then create EmployeeAddress entity otherwise get the
		 * existing EmployeeAddress entity from employee details
		 */
		if (employee.getAddress() == null) {
			address = new EmployeeAddress();
			address.setRecordCreated(dateTime.getCurrentDateTime());
		} else {
			address = employee.getAddress();
			address.setRecordUpdated(dateTime.getCurrentDateTime());
		}

		// Setting the new value of EmployeeAddress entity
		address.setCountry(country);
		address.setState(state);
		address.setCity(city);
		address.setPostalCode(employeeTo.getPostalCode());
		address.setAddressLine1(employeeTo.getAddressLine1());
		address.setAddressLine2(employeeTo.getAddressLine2().isEmpty() ? null : employeeTo.getAddressLine2());

		// Setting the new value of Employee entity
		employee.setFirstName(employeeTo.getFirstName());
		employee.setMiddleName(employeeTo.getMiddleName().isEmpty() ? null : employeeTo.getMiddleName());
		employee.setLastName(employeeTo.getLastName());
		employee.setGender(employeeTo.getGender());
		employee.setMobileNumber(employeeTo.getMobileNumber());
		employee.setAlternateMobileNumber(
				employeeTo.getAlternateMobileNumber().isEmpty() ? null : employeeTo.getAlternateMobileNumber());
		employee.setAlternateEmail(employeeTo.getAlternateEmail().isEmpty() ? null : employeeTo.getAlternateEmail());
		employee.setAddress(address);
		if (employeeTo.getProfilePhoto() != null) {
			employee.setProfilePhoto(employeeTo.getProfilePhoto());
		}
		employee.setRecordUpdated(dateTime.getCurrentDateTime());

		return message;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Integer archiveEmployee(Integer employeeId) {

		Integer noOfRecordUpdated = 0;

		String hql = "UPDATE Employee employee SET employee.archive=?1, employee.recordUpdated=?2 WHERE employee.employeeId=?3 AND employee.archive=?4";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(1, (byte) 1);
		query.setParameter(2, dateTime.getCurrentDateTime());
		query.setParameter(3, employeeId);
		query.setParameter(4, (byte) 0);
		noOfRecordUpdated = query.executeUpdate();

		return noOfRecordUpdated;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Integer unArchiveEmployee(Integer employeeId) {

		Integer noOfRecordUpdated = 0;

		String hql = "UPDATE Employee employee SET employee.archive=?1, employee.recordUpdated=?2 WHERE employee.employeeId=?3";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(1, (byte) 0);
		query.setParameter(2, dateTime.getCurrentDateTime());
		query.setParameter(3, employeeId);
		noOfRecordUpdated = query.executeUpdate();

		return noOfRecordUpdated;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Integer savePassword(EmployeeTO employeeTo) {

		Integer noOfRecordUpdated = 0;

		String hql = "UPDATE Employee employee SET employee.password=?1, employee.tokenUUID=?2, employee.tokenGeneratedDate=?3, employee.recordUpdated=?4 WHERE employee.employeeId=?5 AND employee.archive=?6";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(1, passwordEncoder.encode(employeeTo.getPassword()));
		query.setParameter(2, null);
		query.setParameter(3, null);
		query.setParameter(4, dateTime.getCurrentDateTime());
		query.setParameter(5, employeeTo.getEmployeeId());
		query.setParameter(6, (byte) 0);
		noOfRecordUpdated = query.executeUpdate();

		return noOfRecordUpdated;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Integer activeEmployee(Integer employeeId) {

		Integer noOfRecordUpdated = 0;

		String hql = "UPDATE Employee employee SET employee.active=?1, employee.recordUpdated=?2 WHERE employee.employeeId=?3 AND employee.archive=?4";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(1, (byte) 1);
		query.setParameter(2, dateTime.getCurrentDateTime());
		query.setParameter(3, employeeId);
		query.setParameter(4, (byte) 0);
		noOfRecordUpdated = query.executeUpdate();

		return noOfRecordUpdated;
	}

	@Override
	public Employee updateTokenUUID(Integer employeeId) {

		Employee employee = null;

		employee = (Employee) sessionFactory.getCurrentSession().get(Employee.class, employeeId);
		employee.setTokenUUID(UUID.randomUUID().toString());
		employee.setTokenGeneratedDate(dateTime.getCurrentDateTime());
		employee.setRecordUpdated(dateTime.getCurrentDateTime());

		return employee;
	}

}
