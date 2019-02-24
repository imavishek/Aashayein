/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.controller;
 * @FileName EmployeeRegistrationController.java
 * @CreatedDate 22-Dec-2018
 * Modified by @author avishekdas last on 2018-12-22 16:22:12
 */

package org.avishek.aashayein.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.command.EmployeeRegistrationCommand;
import org.avishek.aashayein.command.PasswordCommand;
import org.avishek.aashayein.dto.EmployeeRoleTO;
import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.dto.EmployeeTitleTO;
import org.avishek.aashayein.exception.InvalidTokenException;
import org.avishek.aashayein.exception.UploadingFailedException;
import org.avishek.aashayein.service.EmployeeRoleAndAccessService;
import org.avishek.aashayein.service.EmployeeService;
import org.avishek.aashayein.service.EmployeeTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/EmployeeRegistration")
public class EmployeeRegistrationController {

	@Autowired
	EmployeeTitleService employeeTitleService;

	@Autowired
	EmployeeRoleAndAccessService employeeRoleAndAccessService;

	@Autowired
	EmployeeService employeeService;

	private static final Logger logger = LogManager.getLogger(EmployeeRegistrationController.class);

	@InitBinder("employee")
	public void customizeBinding(WebDataBinder binder) {

		binder.registerCustomEditor(String.class, "firstName", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "middleName", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "lastName", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "mobileNumber", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "alternateMobileNumber", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "email", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "alternateEmail", new StringTrimmerEditor(false));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "joiningDate", new CustomDateEditor(dateFormat, false));
	}

	// Shows the employee registration page
	@RequestMapping(value = "/showRegistration.abhi")
	public String showRegistrationPage(Model model, HttpServletRequest request) {

		String view = "";
		String breadcrumb = "<a href='" + request.getContextPath() + "'>Home</a> / Admin / <a href='"
				+ request.getContextPath() + "/Employee/showEmployees.abhi'>Employees</a> / <a href='"
				+ request.getContextPath() + "/EmployeeRegistration/showRegistration.abhi'>Employee Registration</a>";

		// Getting all the job title details
		List<EmployeeTitleTO> jobTitles = employeeTitleService.getAllJobTitles();

		// Getting all the employee role details
		List<EmployeeRoleTO> employeeRoles = employeeRoleAndAccessService.getAllRoles();

		/*
		 * If error occurs in employee registration then redirect to this controller
		 * with EmployeeCommand and Binding error as flash attribute
		 */
		if (!model.containsAttribute("employee")) {
			model.addAttribute("employee", new EmployeeRegistrationCommand());
		}

		model.addAttribute("pageTitle", "Add Employee");
		model.addAttribute("breadcrumb", breadcrumb);
		model.addAttribute("jobTitles", jobTitles);
		model.addAttribute("employeeRoles", employeeRoles);

		view = "employeeRegistration";

		return view;
	}

	// Adding employee in database
	@PostMapping(value = "/saveEmployee.abhi")
	public String saveEmployee(Model model,
			@Valid @ModelAttribute("employee") EmployeeRegistrationCommand employeeCommand, BindingResult result,
			HttpServletRequest request, RedirectAttributes redir) throws UploadingFailedException {

		String view = "";

		// Checking data binding error
		if (result.hasErrors()) {

			// Logging DataBinding Error
			for (FieldError error : result.getFieldErrors()) {
				logger.error("Error In DataBinding For Field:- " + error.getField() + " FieldValue:- "
						+ error.getRejectedValue());
			}

			// Redirect to show the error registration page
			redir.addFlashAttribute("employee", employeeCommand);
			redir.addFlashAttribute("org.springframework.validation.BindingResult.employee", result);

			view = "redirect:/EmployeeRegistration/showRegistration.abhi";
		} else {

			// Setting value in Employee Transfer Object
			EmployeeTO employeeTo = new EmployeeTO();

			employeeTo.setFirstName(employeeCommand.getFirstName());
			employeeTo.setMiddleName(employeeCommand.getMiddleName());
			employeeTo.setLastName(employeeCommand.getLastName());
			employeeTo.setGender(employeeCommand.getGender());
			employeeTo.setMobileNumber(employeeCommand.getMobileNumber());
			employeeTo.setAlternateMobileNumber(employeeCommand.getAlternateMobileNumber());
			employeeTo.setEmail(employeeCommand.getEmail());
			employeeTo.setAlternateEmail(employeeCommand.getAlternateEmail());
			employeeTo.setJobTitleId(employeeCommand.getTitle());
			employeeTo.setRoleId(employeeCommand.getRole());
			employeeTo.setJoiningDate(employeeCommand.getJoiningDate());
			employeeTo.setProfilePhotoFile(employeeCommand.getProfilePhoto());

			// Adding the employee
			String message = employeeService.addEmployee(employeeTo);

			// If error does not occurs then message is empty
			if (message == null) {
				logger.info("Employee " + employeeCommand.getFirstName() + " " + employeeCommand.getMiddleName() + " "
						+ employeeCommand.getLastName() + " Added Successfully");

				// Sending the message and message type to the corresponding jsp page
				redir.addFlashAttribute("message", "Employee Added Successfully");
				redir.addFlashAttribute("messageType", "Success");

				view = "redirect:/Employee/showEmployees.abhi";
			} else {
				logger.error("Failed To Add Employee " + employeeCommand.getFirstName() + " "
						+ employeeCommand.getMiddleName() + " " + employeeCommand.getLastName());
				redir.addFlashAttribute("employee", employeeCommand);
				redir.addFlashAttribute("message", message);
				redir.addFlashAttribute("messageType", "Error");

				view = "redirect:/EmployeeRegistration/showRegistration.abhi";
			}
		}

		return view;
	}

	// Show setPassword page for employee
	@RequestMapping(value = "/showSetPassword.abhi")
	public String showSetPassword(Model model, HttpServletRequest request,
			@RequestParam(name = "token", required = true) String token) throws InvalidTokenException {

		String view = "";
		// Token expiration time in milliseconds. 24h = 86400000 Milliseconds
		Long expiration = 86400000l;

		// Verify token and expired date
		EmployeeTO employee = employeeService.verifyToken(token, expiration);

		if (employee == null) {
			throw new InvalidTokenException("Invalid Token");
		}

		if (!model.containsAttribute("password")) {
			PasswordCommand password = new PasswordCommand();
			password.setTokenUUID(employee.getTokenUUID());
			model.addAttribute("password", password);
		}

		view = "setPassword";

		return view;
	}

	// Saving password
	@RequestMapping(value = "/setPassword.abhi")
	public String setPassword(Model model, @Valid @ModelAttribute("password") PasswordCommand password,
			BindingResult result, HttpServletRequest request, RedirectAttributes redir) {

		String view = "";

		// Checking data binding error
		if (result.hasErrors()) {

			// Logging DataBinding Error
			for (FieldError error : result.getFieldErrors()) {
				logger.error("Error In DataBinding For Field:- " + error.getField() + " FieldValue:- "
						+ error.getRejectedValue());
			}

			// Redirect to show the error registration page
			redir.addFlashAttribute("password", password);
			redir.addFlashAttribute("org.springframework.validation.BindingResult.password", result);

			view = "redirect:/EmployeeRegistration/showSetPassword.abhi?token=" + password.getTokenUUID();
		} else {
			System.out.println(password);
		}
		view = "setPassword";

		return view;
	}

}
