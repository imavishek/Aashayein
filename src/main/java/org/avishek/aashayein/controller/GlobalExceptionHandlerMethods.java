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
import org.avishek.aashayein.exception.EmployeeRoleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
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
	@ExceptionHandler({ NoHandlerFoundException.class, MissingServletRequestParameterException.class })
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

	// Generic Exception Handler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public String handleException(Model model, Exception e) {

		String view = "";

		logger.error(e.getMessage() + " [Exception " + e.getClass() + "]");

		model.addAttribute("title", "Server Error");
		model.addAttribute("errorTitle", "Internal Server Error");
		model.addAttribute("errorMessage",
				"The server has encountered an unexpected error. Please contact administrator");

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
		model.addAttribute("errorMessage", "The employee role you are looking for might have been deactivated or unavailable");

		view = "error500";

		return view;

	}

}
