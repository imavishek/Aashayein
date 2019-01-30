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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.command.EmployeeCommand;
import org.avishek.aashayein.dto.EmployeeRoleTO;
import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.dto.EmployeeTitleTO;
import org.avishek.aashayein.exception.EmployeeEmailExistsException;
import org.avishek.aashayein.exception.EmployeeMobileNumberExistsException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/EmployeeRegistration")
public class EmployeeRegistrationController {

	private static final Logger logger = LogManager.getLogger(EmployeeRegistrationController.class);

	@Autowired
	EmployeeTitleService employeeTitleService;

	@Autowired
	EmployeeRoleAndAccessService employeeRoleAndAccessService;

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	private ServletContext servletContext;

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
				+ request.getContextPath() + "/EmployeeRegistration/showRegistration.abhi'>Employee Registration</a>";

		// Getting all the job title details
		List<EmployeeTitleTO> jobTitles = employeeTitleService.getAllJobTitles();

		// Getting all the employee role details
		List<EmployeeRoleTO> employeeRoles = employeeRoleAndAccessService.getAllRoles();

		EmployeeCommand employee = new EmployeeCommand();

		model.addAttribute("pageTitle", "Add Employee");
		model.addAttribute("breadcrumb", breadcrumb);
		model.addAttribute("employee", employee);
		model.addAttribute("jobTitles", jobTitles);
		model.addAttribute("employeeRoles", employeeRoles);

		view = "employeeRegistration";

		return view;
	}

	// Adding and editing employee in database
	@PostMapping(value = "/saveEmployee.abhi")
	public String registerEmployee(Model model, @Valid @ModelAttribute("employee") EmployeeCommand employeeCommand,
			BindingResult result, HttpServletRequest request, RedirectAttributes redir)
			throws EmployeeEmailExistsException, EmployeeMobileNumberExistsException {

		String view = "";
		String breadcrumb = "";
		String redirectUrl = "";

		// Checking data binding error
		if (result.hasErrors()) {

			// Logging DataBinding Error
			for (FieldError error : result.getFieldErrors()) {
				logger.error("Error In DataBinding For Field:- " + error.getField() + " FieldValue:- "
						+ error.getRejectedValue());
			}

			// Getting all the job title details
			List<EmployeeTitleTO> jobTitles = employeeTitleService.getAllJobTitles();

			// Getting all the employee role details
			List<EmployeeRoleTO> employeeRoles = employeeRoleAndAccessService.getAllRoles();
			
			view = "employeeRegistration";

			if (employeeCommand.getEmployeeId() == "") {

				breadcrumb = "<a href='" + request.getContextPath() + "'>Home</a> / Admin / <a href='"
						+ request.getContextPath()
						+ "/EmployeeRegistration/showEmployees.abhi'>Employees</a> / <a href='"
						+ request.getContextPath() + "/EmployeeRegistration/showRegistration.abhi'>Add Employee</a>";

				model.addAttribute("pageTitle", "Add Employee");
			} else {

				breadcrumb = "<a href='" + request.getContextPath() + "'>Home</a> / Admin / <a href='"
						+ request.getContextPath()
						+ "/EmployeeRegistration/showEmployees.abhi'>Employees</a> / <a href='"
						+ request.getContextPath() + "/EmployeeRegistration/showEditEmployee.abhi?employeeId="
						+ employeeCommand.getEmployeeId() + "'> Edit Employee</a>";

				model.addAttribute("pageTitle", "Edit Employee");
			}

			model.addAttribute("breadcrumb", breadcrumb);
			model.addAttribute("employee", employeeCommand);
			model.addAttribute("jobTitles", jobTitles);
			model.addAttribute("employeeRoles", employeeRoles);
		} else {

//			// Setting value in Employee Transfer Object
//			EmployeeTO employeeTo = new EmployeeTO();
//
//			employeeTo.setFirstName(employeeCommand.getFirstName());
//			employeeTo.setMiddleName(employeeCommand.getMiddleName());
//			employeeTo.setLastName(employeeCommand.getLastName());
//			employeeTo.setGender(employeeCommand.getGender());
//			employeeTo.setJobTitleId(employeeCommand.getTitle());
//			employeeTo.setRoleId(employeeCommand.getRole());
//			employeeTo.setMobileNumber(employeeCommand.getMobileNumber());
//			employeeTo.setAlternateMobileNumber(employeeCommand.getAlternateMobileNumber());
//			employeeTo.setEmail(employeeCommand.getEmail());
//			employeeTo.setAlternateEmail(employeeCommand.getAlternateEmail());
//			employeeTo.setJoiningDate(employeeCommand.getJoiningDate());
//			employeeTo.setProfilePhotoFile(employeeCommand.getProfilePhoto());
//
//			// Add employee if employeeId is empty otherwise edit
//			if (employeeCommand.getEmployeeId().isEmpty()) {
//
//				// Adding the employee
//				boolean success = employeeService.addEmployee(employeeTo);
//
//				if (success) {
//					logger.info("Employee " + employeeCommand.getFirstName() + " " + employeeCommand.getMiddleName()
//							+ " " + employeeCommand.getLastName() + " Added Successfully");
//
//					// Sending the message and message type to the corresponding jsp page
//					redir.addFlashAttribute("message", "Employee Added Successfully");
//					redir.addFlashAttribute("messageType", "Success");
//				} else {
//					logger.error("Failed To Add Employee " + employeeCommand.getFirstName() + " "
//							+ employeeCommand.getMiddleName() + " " + employeeCommand.getLastName());
//					redir.addFlashAttribute("message", "Failed To Add Employee");
//					redir.addFlashAttribute("messageType", "Error");
//				}
//			} else {
//
//				System.out.println(employeeCommand);
//			}
//
//			redirectUrl = "/EmployeeRegistration/showEmployees.abhi";
//
//			view = "redirect:" + redirectUrl;
			
			System.out.println(servletContext.getContextPath());
		}

		return view;

	}
}
