package org.avishek.aashayein.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.command.EditEmployeeCommand;
import org.avishek.aashayein.dto.EmployeeRoleTO;
import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.dto.EmployeeTitleTO;
import org.avishek.aashayein.exception.EmployeeNotFoundException;
import org.avishek.aashayein.service.EmployeeRoleAndAccessService;
import org.avishek.aashayein.service.EmployeeService;
import org.avishek.aashayein.service.EmployeeTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/Employee")
public class EmployeeController {

	@Autowired
	EmployeeTitleService employeeTitleService;

	@Autowired
	EmployeeRoleAndAccessService employeeRoleAndAccessService;

	@Autowired
	EmployeeService employeeService;

	private static final Logger logger = LogManager.getLogger(EmployeeController.class);

	// Shows the list of employee page
	@RequestMapping(value = "/showEmployees.abhi")
	public String showEmployees(Model model, HttpServletRequest request) {

		String view = "";
		String breadcrumb = "<a href='" + request.getContextPath() + "'>Home</a> / Admin / <a href='"
				+ request.getContextPath() + "/Employee/showEmployees.abhi'>Employees</a>";

		model.addAttribute("pageTitle", "Employees");
		model.addAttribute("breadcrumb", breadcrumb);

		view = "employees";

		return view;
	}

	// Get all employee details
	@GetMapping(value = "/getEmployees.abhi", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<EmployeeTO> getEmployees(Model model, HttpServletRequest request) {

		// Getting all the employee details
		List<EmployeeTO> employees = employeeService.getAllEmployees();

		return employees;
	}

	// Shows edit employee page
	@RequestMapping(value = "/showEditEmployee.abhi")
	public String showEditEmployee(Model model, HttpServletRequest request,
			@RequestParam(name = "employeeId") String id, @RequestParam String employeeCode)
			throws EmployeeNotFoundException {

		String view = "";
		String breadcrumb = "<a href='" + request.getContextPath() + "'>Home</a> / Admin / <a href='"
				+ request.getContextPath() + "/Employee/showEmployees.abhi'>Employees</a> / <a href='"
				+ request.getContextPath() + "/Employee/showEditEmployee.abhi?employeeId=" + id + "&employeeCode="
				+ employeeCode + "'>Edit Employee: " + employeeCode + "</a>";

		Integer employeeId = Integer.parseInt(id);

		// Checking the existence of the employee
		EmployeeTO employeeTo = employeeService.getEmployeeDetailsById(employeeId);

		// If employee not found then throw EmployeeNotFoundException
		if (employeeTo == null)
			throw new EmployeeNotFoundException(employeeCode);

		// Getting all the job title details
		List<EmployeeTitleTO> jobTitles = employeeTitleService.getAllJobTitles();

		// Getting all the employee role details
		List<EmployeeRoleTO> employeeRoles = employeeRoleAndAccessService.getAllRoles();

		EditEmployeeCommand editEmployeeCommand = new EditEmployeeCommand();
		editEmployeeCommand.setEmployeeId(employeeTo.getEmployeeId().toString());
		editEmployeeCommand.setTitle(employeeTo.getJobTitleId());
		editEmployeeCommand.setRole(employeeTo.getRoleId());
		
		model.addAttribute("editEmployee", editEmployeeCommand);
		model.addAttribute("employee", employeeTo);
		model.addAttribute("jobTitles", jobTitles);
		model.addAttribute("employeeRoles", employeeRoles);
		model.addAttribute("pageTitle", "Employee Code: " + employeeCode);
		model.addAttribute("breadcrumb", breadcrumb);

		view = "editEmployee";

		return view;
	}
}
