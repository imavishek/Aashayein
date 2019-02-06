package org.avishek.aashayein.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.service.EmployeeRoleAndAccessService;
import org.avishek.aashayein.service.EmployeeService;
import org.avishek.aashayein.service.EmployeeTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
