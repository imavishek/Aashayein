/**
 * @Project_Name Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.exception;
 * @File_Name GlobalExceptionHandlerMethods.java
 * @Created_Date 17-Oct-2018
 * Modified by @author avishekdas last on 2018-10-17 00:30:23
 */

package org.avishek.aashayein.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.exception.EmployeeEmailExistsException;
import org.avishek.aashayein.exception.EmployeeMobileNumberExistsException;
import org.avishek.aashayein.exception.EmployeeNotFoundException;
import org.avishek.aashayein.exception.EmployeeRoleNotFoundException;
import org.avishek.aashayein.exception.EmployeeTitleNotFoundException;
import org.avishek.aashayein.exception.InvalidTokenException;
import org.avishek.aashayein.exception.UploadingFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionException;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandlerMethods {

	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandlerMethods.class);

	/*
	 * Handler Not Found Exception And Missing Servlet Request Parameter Exception
	 * Handler
	 */
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	/*
	 * Consider missing parameter of handler (Controller) as
	 * NoHandlerFoundException. So return HttpStatus.NOT_FOUND instead of
	 * HttpStatus.INTERNAL_SERVER_ERROR
	 */
	@ExceptionHandler({ NoHandlerFoundException.class, MissingServletRequestParameterException.class,
			HttpRequestMethodNotSupportedException.class })
	public String handlerNoHandlerFoundException(Model model, Exception e) {

		String view = "";

		logger.error(e.getMessage() + " [Exception " + e.getClass() + "]");

		model.addAttribute("title", "File Not Found");
		model.addAttribute("errorTitle", "File Not Found");
		model.addAttribute("errorMessage",
				"The page you are looking for might have been removed had its name changed or is temporarily unavailable.");

		view = "error404";

		return view;
	}

	// Unable to acquire JDBC Connection
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = TransactionException.class)
	public String handleGenericJDBCException(Model model, TransactionException e) {

		String view = "";

		logger.error(e.getMessage() + " [Exception " + e.getClass() + "]");

		model.addAttribute("title", "Database Error");
		model.addAttribute("errorTitle", "Failed to connect with Database");
		model.addAttribute("errorMessage", "The server failed to connect with database. Please contact administrator.");

		view = "error500";

		return view;

	}

	// Employee Role Not Found Exception Handler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = EmployeeRoleNotFoundException.class)
	public String handleEmployeeRoleNotFoundException(EmployeeRoleNotFoundException e, Model model) {

		String view = "";

		logger.error("Employee Role Not Found (Either Unavailable Or Deactivated) For RoleId - " + e.getMessage());

		model.addAttribute("title", "Role UnAvailable");
		model.addAttribute("errorTitle", "Employee Role Not Found");
		model.addAttribute("errorMessage",
				"The employee role you are looking for might have been deactivated or unavailable.");

		view = "error500";

		return view;

	}

	// Employee Title Not Found Exception Handler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = EmployeeTitleNotFoundException.class)
	public String handleEmployeeTitleNotFoundException(EmployeeTitleNotFoundException e, Model model) {

		String view = "";

		logger.error("Employee Title Not Found (Either Unavailable Or Deactivated) For TitleId - " + e.getMessage());

		model.addAttribute("title", "JobTitle UnAvailable");
		model.addAttribute("errorTitle", "Employee Title Not Found");
		model.addAttribute("errorMessage",
				"The employee title you are looking for might have been deactivated or unavailable.");

		view = "error500";

		return view;

	}

	// Employee Not Found Exception Handler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public String handleEmployeeNotFoundException(EmployeeNotFoundException e, Model model) {

		String view = "";

		logger.error("Employee Not Found (Either Unavailable Or Deactivated) For EmployeeId/EmployeeCode: "
				+ e.getMessage());

		model.addAttribute("title", "Employee UnAvailable");
		model.addAttribute("errorTitle", "Employee Not Found");
		model.addAttribute("errorMessage",
				"The employee details you are looking for might have been deactivated or unavailable.");

		view = "error500";

		return view;

	}

	// Employee Email Exists Exception Handler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = EmployeeEmailExistsException.class)
	public String handleEmployeeEmailExistsException(EmployeeEmailExistsException e, Model model) {

		String view = "";

		logger.error("Employee Email Exists - " + e.getMessage());

		model.addAttribute("title", "Email Exists");
		model.addAttribute("errorTitle", "Employee Email Exists");
		model.addAttribute("errorMessage",
				"The emailId through which you want to register is already exists, Please register with a different emailId.");

		view = "error500";

		return view;

	}

	// Employee MobileNumber Exists Exception Handler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = EmployeeMobileNumberExistsException.class)
	public String handleEmployeeMobileNumberExistsException(EmployeeMobileNumberExistsException e, Model model) {

		String view = "";

		logger.error("Employee MobileNumber Exists - " + e.getMessage());

		model.addAttribute("title", "MobileNumber Exists");
		model.addAttribute("errorTitle", "Employee MobileNumber Exists");
		model.addAttribute("errorMessage",
				"The mobileNumber through which you want to register is already exists, Please register with a different mobileNumber.");

		view = "error500";

		return view;

	}

	// File Uploading Failed Exception Handler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = UploadingFailedException.class)
	public String handleFileUploadingFailedException(UploadingFailedException e, Model model) {

		String view = "";

		logger.error(e.getMessage());

		model.addAttribute("title", "Uploading Failed");
		model.addAttribute("errorTitle", "File Uploading Failed");
		model.addAttribute("errorMessage",
				"The server has encountered an unexpected error during file uploading. Please retry or contact administrator.");

		view = "error500";

		return view;

	}

	// Invalid Token Exception Handler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = InvalidTokenException.class)
	public String handleInvalidTokenException(InvalidTokenException e, Model model) {

		String view = "";

		logger.error(e.getMessage());

		model.addAttribute("title", "Invalid Token");
		model.addAttribute("errorTitle", "Invalid Token");
		model.addAttribute("errorMessage",
				"The link you are trying to access is expired. Please retry with a new link.");

		view = "error500";

		return view;

	}

	// Generic Exception Handler
//	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler(value = Exception.class)
//	public String handleException(Model model, Exception e) {
//
//		String view = "";
//
//		logger.error(e.getMessage() + " [Exception " + e.getClass() + "]");
//
//		model.addAttribute("title", "Server Error");
//		model.addAttribute("errorTitle", "Internal Server Error");
//		model.addAttribute("errorMessage",
//				"The server has encountered an unexpected error. Please contact administrator.");
//
//		view = "error500";
//
//		return view;
//
//	}
}
