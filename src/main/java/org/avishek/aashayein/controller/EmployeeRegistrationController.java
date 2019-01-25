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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.command.EmployeeCommand;
import org.avishek.aashayein.dto.EmployeeRoleTO;
import org.avishek.aashayein.dto.EmployeeTitleTO;
import org.avishek.aashayein.service.EmployeeRoleAndAccessService;
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

@Controller
@RequestMapping(value = "/EmployeeRegistration")
public class EmployeeRegistrationController {

	private static final Logger logger = LogManager.getLogger(EmployeeRegistrationController.class);

	@Autowired
	EmployeeTitleService employeeTitleService;

	@Autowired
	EmployeeRoleAndAccessService employeeRoleAndAccessService;

	@InitBinder("employee")
	public void customizeBinding(WebDataBinder binder) {

		binder.registerCustomEditor(String.class, "firstName", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "middleName", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "lastName", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "mobileNumber", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "alternateMobileNumber", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "email", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "alternateEmail", new StringTrimmerEditor(false));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "joiningDate", new CustomDateEditor(dateFormat, false));

	}

	// Shows the employee registration page
	@RequestMapping(value = "/showRegistration.abhi")
	public String showRegistrationPage(Model model, HttpServletRequest request) {

		logger.info("Showing Employee Registration Page");

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

	@PostMapping(value = "/register.abhi")
	public String registerEmployee(Model model, @Valid @ModelAttribute("employee") EmployeeCommand employee,
			BindingResult result, HttpServletRequest request) {

		String view = "";
		String breadcrumb = "";

		Map<Integer, String> title = new LinkedHashMap<Integer, String>();
		title.put(1, "United Stated");
		title.put(2, "China");
		title.put(3, "Singapore");
		title.put(4, "Malaysia");

		if (result.hasErrors()) {

			// Logging DataBinding Error
			for (FieldError error : result.getFieldErrors()) {
				logger.error("Error In DataBinding For Field:- " + error.getField());
			}

			view = "employeeRegistration";
			breadcrumb = "<a href='" + request.getContextPath() + "'>Home</a> / Admin / <a href='"
					+ request.getContextPath() + "/EmployeeRegistration/showPage.abhi'>Employee</a>";
			model.addAttribute("employee", employee);
			model.addAttribute("pageTitle", "Add Employee");
			model.addAttribute("breadcrumb", breadcrumb);
			model.addAttribute("title", title);
		} else {
			System.out.println(employee.getJoiningDate());
			view = "";
		}

		return view;

	}
}
