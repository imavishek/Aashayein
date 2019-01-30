/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.serviceImpl;
 * @FileName EmployeeServiceImpl.java
 * @CreatedDate 29-Jan-2019
 * Modified by @author avishekdas last on 2019-01-29 23:48:01
 */

package org.avishek.aashayein.serviceImpl;

import org.avishek.aashayein.dao.EmployeeDao;
import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.exception.EmployeeEmailExistsException;
import org.avishek.aashayein.exception.EmployeeMobileNumberExistsException;
import org.avishek.aashayein.service.EmployeeService;
import org.avishek.aashayein.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private FileUploadService fileUploadService;

	// Getting the next employee code from database
	@Override
	@Transactional
	public String getNextEmployeeCode() {

		String employeeCode = null;
		Integer code = null;

		String lastEmployeeCode = employeeDao.getLastEmployeeCode();

		// For 1st employee the employeeCode is "asha-0001" after that it increased by
		// 1;
		if (lastEmployeeCode == null) {
			employeeCode = "asha-00001";
		} else {
			code = Integer.parseInt(lastEmployeeCode.split("-")[1]);
			code += 1;
			employeeCode = "asha-" + code;
		}

		return employeeCode;
	}

	// Adding Employee Role With Module Permissions
	@Override
	@Transactional
	public boolean addEmployee(EmployeeTO employeeTo)
			throws EmployeeEmailExistsException, EmployeeMobileNumberExistsException {

		String employeeCode = null;
		String fileName = null;

		// Checking the existence of Email and PhoneNo
		if (emailExist(employeeTo.getEmail())) {
			throw new EmployeeEmailExistsException(employeeTo.getEmail());
		} else if (mobileNumberExist(employeeTo.getMobileNumber())) {
			throw new EmployeeMobileNumberExistsException(employeeTo.getMobileNumber());
		}

		// Get the next employee code
		employeeCode = getNextEmployeeCode();

		// save profile photo in server rename the file with UUID
		fileName = fileUploadService.uploadProfilePictureIntoServer(employeeTo.getProfilePhotoFile(), employeeCode);

		// Setting the employee code, password and profilrPicture fileName to EmployeeTO
		employeeTo.setEmployeeCode(employeeCode);
		employeeTo.setProfilePhoto(fileName);

		return employeeDao.addEmployee(employeeTo);
	}

	// Checking existence of email
	@Override
	@Transactional
	public boolean emailExist(String email) {

		EmployeeTO employee = employeeDao.getEmployeeByEmail(email);

		if (employee != null) {
			return true;
		}

		return false;
	}

	// Checking existence of mobileNumber
	@Override
	@Transactional
	public boolean mobileNumberExist(String mobileNumber) {

		EmployeeTO employee = employeeDao.getEmployeeByMobileNumber(mobileNumber);

		if (employee != null) {
			return true;
		}

		return false;
	}

}
