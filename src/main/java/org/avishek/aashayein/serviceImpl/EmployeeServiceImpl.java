/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.serviceImpl;
 * @FileName EmployeeServiceImpl.java
 * @CreatedDate 29-Jan-2019
 * Modified by @author avishekdas last on 2019-01-29 23:48:01
 */

package org.avishek.aashayein.serviceImpl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.dao.EmployeeDao;
import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.dto.MailCheckerTO;
import org.avishek.aashayein.entities.Employee;
import org.avishek.aashayein.event.OnRegistrationSuccessEvent;
import org.avishek.aashayein.exception.UploadingFailedException;
import org.avishek.aashayein.service.EmployeeService;
import org.avishek.aashayein.uniquekeyGenerator.UniquekeyGenerator;
import org.avishek.aashayein.utility.FileUploadUtil;
import org.avishek.aashayein.utility.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private FileUploadUtil fileUploadUtil;

	@Autowired
	ApplicationEventPublisher eventPublisher;

	@Autowired
	private UniquekeyGenerator uniquekeyGenerator;

	@Autowired
	private MailUtil mailUtil;

	private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

	// Getting list of all employee details
	@Override
	@Transactional
	public List<EmployeeTO> getAllEmployees() {

		return employeeDao.getAllEmployees();
	}

	// Getting employee details by employeeId
	@Override
	@Transactional
	public EmployeeTO getEmployeeDetailsById(Integer employeeId) {

		return employeeDao.getEmployeeDetailsById(employeeId);
	}

	// Adding Employee Details
	@Override
	@Transactional
	public String addEmployee(EmployeeTO employeeTo) throws UploadingFailedException {

		String message = null;
		String fileName = null;
		String employeeCode = null;
		MailCheckerTO mailCheckerTO = null;

		// Checking the existence of Email and PhoneNo
		if (emailExist(employeeTo.getEmail())) {
			// throw new EmployeeEmailExistsException(employeeTo.getEmail());

			logger.error("Employee Email Exists - " + employeeTo.getEmail());
			message = "EmailId Already Exists";

			return message;
		} else if (mobileNumberExist(employeeTo.getMobileNumber())) {
			// throw new EmployeeMobileNumberExistsException(employeeTo.getMobileNumber());

			logger.error("Employee MobileNumber Exists - " + employeeTo.getMobileNumber());
			message = "MobileNumber Already Exists";

			return message;
		}

		// SMTP verification of mailId
		mailCheckerTO = mailUtil.checkEmailExistence(employeeTo.getEmail());

		if (mailCheckerTO.getSmtp_check() == false) {
			logger.info("SMTP verification failed for mailId:- " + mailCheckerTO.getEmail());
			message = "EmailId SMTP verification failed";

			return message;
		}

		// Getting the next employeecode
		employeeCode = uniquekeyGenerator.getNextEmployeeCode();
		employeeTo.setEmployeeCode(employeeCode);

		// save profile photo in server, rename the file with UUID
		if (!employeeTo.getProfilePhotoFile().isEmpty()) {
			fileName = fileUploadUtil.uploadProfilePictureIntoServer(employeeTo.getProfilePhotoFile(), employeeCode);

			if (fileName == null) {
				throw new UploadingFailedException("Failed to upload profile picture");
			}
		}

		employeeTo.setProfilePhoto(fileName);

		// Save the employee details in database
		Employee employee = employeeDao.addEmployee(employeeTo);

		if (employee == null) {
			logger.info("Failed to save employee details in database");
			message = "Failed To Save Employee Details";

			return message;
		}

		// Publish an event to send registration success mail
		eventPublisher.publishEvent(new OnRegistrationSuccessEvent(this, employee));

		return message;
	}

	// Editing Employee Details
	@Override
	@Transactional
	public String editEmployee(EmployeeTO employeeTo) {

		return employeeDao.editEmployee(employeeTo);
	}

	// Editing Employee Profile
	@Override
	@Transactional
	public String editEmployeeProfile(EmployeeTO employeeTo) throws UploadingFailedException {

		String message = null;
		String fileName = null;
		// session
		String employeeCode = "aasha-0001";

		// Checking the existence PhoneNo
		if (mobileNumberExist(employeeTo.getMobileNumber())) {

			logger.error("MobileNumber Already Exists - " + employeeTo.getMobileNumber());
			message = "MobileNumber Already Exists";

			return message;
		}

		// save profile photo in server, rename the file with UUID
		if (!employeeTo.getProfilePhotoFile().isEmpty()) {
			fileName = fileUploadUtil.uploadProfilePictureIntoServer(employeeTo.getProfilePhotoFile(), employeeCode);

			if (fileName == null) {
				throw new UploadingFailedException("Failed to upload profile picture");
			}
		}

		employeeTo.setProfilePhoto(fileName);

		return employeeDao.editEmployeeProfile(employeeTo);
	}

	// Archive Employee
	@Override
	@Transactional
	public Integer archiveEmployee(Integer employeeId) {

		return employeeDao.archiveEmployee(employeeId);
	}

	// UnArchive Employee
	@Override
	@Transactional
	public Integer unArchiveEmployee(Integer employeeId) {

		return employeeDao.unArchiveEmployee(employeeId);
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
