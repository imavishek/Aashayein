/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.serviceImpl;
 * @FileName EmployeeServiceImpl.java
 * @CreatedDate 29-Jan-2019
 * Modified by @author avishekdas last on 2019-01-29 23:48:01
 */

package org.avishek.aashayein.serviceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.dao.EmployeeDao;
import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.dto.MailCheckerTO;
import org.avishek.aashayein.event.OnRegistrationCompleteEvent;
import org.avishek.aashayein.eventHandler.OnRegistrationCompleteEventListener;
import org.avishek.aashayein.exception.EmployeeEmailExistsException;
import org.avishek.aashayein.exception.EmployeeMobileNumberExistsException;
import org.avishek.aashayein.exception.UploadingFailedException;
import org.avishek.aashayein.primarykeyGenerator.PrimaryKeyGenerator;
import org.avishek.aashayein.service.EmployeeService;
import org.avishek.aashayein.utility.FileUploadUtil;
import org.avishek.aashayein.utility.MailSenderUtil;
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
	private MailSenderUtil mailSenderUtil;

	@Autowired
	private PrimaryKeyGenerator primaryKeyGenerator;

	private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

	// Adding Employee Role With Module Permissions
	@Override
	@Transactional
	public String addEmployee(EmployeeTO employeeTo) throws UploadingFailedException {

		String message = null;
		String employeeCode = null;
		String fileName = null;
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
		mailCheckerTO = mailSenderUtil.checkEmailExistence(employeeTo.getEmail());

		if (mailCheckerTO.getSmtp_check() == false) {
			logger.info("SMTP verification failed for mailId:- " + mailCheckerTO.getEmail());
			message = "EmailId SMTP verification failed";

			return message;
		}

		employeeCode = primaryKeyGenerator.getNextEmployeeIdCode().getEmployeeCode();

		// save profile photo in server, rename the file with UUID
		fileName = fileUploadUtil.uploadProfilePictureIntoServer(employeeTo.getProfilePhotoFile(), employeeCode);
		if (fileName == null) {
			throw new UploadingFailedException("Failed to upload profile picture");
		}

//
//		// Setting the employee code, password and profilrPicture fileName to EmployeeTO
//		employeeTo.setEmployeeCode(employeeCode);
//		employeeTo.setProfilePhoto(fileName);
//
//		employeeDao.addEmployee(employeeTo);
//
//		// Publish an event to send registration success mail
//		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(this, employeeTo));

		return message;
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
