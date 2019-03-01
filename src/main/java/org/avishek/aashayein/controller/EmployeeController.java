package org.avishek.aashayein.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.command.EditEmployeeCommand;
import org.avishek.aashayein.dto.EmployeeRoleTO;
import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.dto.EmployeeTitleTO;
import org.avishek.aashayein.exception.EmployeeNotFoundException;
import org.avishek.aashayein.exception.UploadingFailedException;
import org.avishek.aashayein.service.EmployeeRoleAndAccessService;
import org.avishek.aashayein.service.EmployeeService;
import org.avishek.aashayein.service.EmployeeTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/Admin/Employee")
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
				+ request.getContextPath() + "/Admin/Employee/showEmployees.abhi'>Employees</a>";

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
				+ request.getContextPath() + "/Admin/Employee/showEmployees.abhi'>Employees</a> / <a href='"
				+ request.getContextPath() + "/Admin/Employee/showEditEmployee.abhi?employeeId=" + id + "&employeeCode="
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

		/*
		 * If error occurs in during edit employee then redirect to this controller with
		 * EditEmployeeCommand and Binding error as flash attribute
		 */
		if (!model.containsAttribute("editEmployee")) {
			EditEmployeeCommand editEmployeeCommand = new EditEmployeeCommand();
			editEmployeeCommand.setEmployeeId(employeeTo.getEmployeeId().toString());
			editEmployeeCommand.setEmployeeCode(employeeTo.getEmployeeCode());
			editEmployeeCommand.setTitle(employeeTo.getJobTitleId());
			editEmployeeCommand.setRole(employeeTo.getRoleId());
			editEmployeeCommand.setJoiningDate(employeeTo.getJoiningDate());

			model.addAttribute("editEmployee", editEmployeeCommand);
		}

		model.addAttribute("employee", employeeTo);
		model.addAttribute("jobTitles", jobTitles);
		model.addAttribute("employeeRoles", employeeRoles);
		model.addAttribute("pageTitle", "Employee Code: " + employeeCode);
		model.addAttribute("breadcrumb", breadcrumb);

		view = "editEmployee";

		return view;
	}

	// Edit employee
	@PostMapping(value = "/saveEmployee.abhi")
	public String saveEmployee(Model model,
			@Valid @ModelAttribute("editEmployee") EditEmployeeCommand editEmployeeCommand, BindingResult result,
			HttpServletRequest request, RedirectAttributes redir) throws UploadingFailedException {

		String view = "";

		// Checking data binding error
		if (result.hasErrors()) {

			// Logging DataBinding Error
			for (FieldError error : result.getFieldErrors()) {
				logger.error("Error In DataBinding For Field:- " + error.getField() + " FieldValue:- "
						+ error.getRejectedValue());
			}

			// Redirect to show the error page during edit employee
			redir.addFlashAttribute("editEmployee", editEmployeeCommand);
			redir.addFlashAttribute("org.springframework.validation.BindingResult.editEmployee", result);

			view = "redirect:/Admin/Employee/showEditEmployee.abhi?employeeId=" + editEmployeeCommand.getEmployeeId()
					+ "&employeeCode=" + editEmployeeCommand.getEmployeeCode();
		} else {

			// Setting value in Employee Transfer Object
			EmployeeTO employeeTo = new EmployeeTO();

			employeeTo.setEmployeeId(Integer.parseInt(editEmployeeCommand.getEmployeeId()));
			employeeTo.setEmployeeCode(editEmployeeCommand.getEmployeeCode());
			employeeTo.setJobTitleId(editEmployeeCommand.getTitle());
			employeeTo.setRoleId(editEmployeeCommand.getRole());
			employeeTo.setJoiningDate(editEmployeeCommand.getJoiningDate());

			// Editing the employee
			String message = employeeService.editEmployee(employeeTo);

			// If error does not occurs then message is empty
			if (message == null) {
				logger.info("Employee Details Edited Successfully, Employee Code: "
						+ editEmployeeCommand.getEmployeeCode());

				// Sending the message and message type to the corresponding jsp page
				redir.addFlashAttribute("message", "Employee Edited Successfully");
				redir.addFlashAttribute("messageType", "Success");

				view = "redirect:/Admin/Employee/showEmployees.abhi";
			} else {
				logger.error("Failed To Edit Employee Having EmployeeCode: " + editEmployeeCommand.getEmployeeCode());
				redir.addFlashAttribute("employee", editEmployeeCommand);
				redir.addFlashAttribute("message", message);
				redir.addFlashAttribute("messageType", "Error");

				view = "redirect:/Admin/Employee/showEditEmployee.abhi";
			}
		}

		return view;
	}

	// Archive employee
	@RequestMapping(value = "/archiveEmployee.abhi")
	public String archiveEmployee(Model model, HttpServletRequest request, @RequestParam(name = "employeeId") String Id,
			RedirectAttributes redir) throws EmployeeNotFoundException {

		String view = "";
		String redirectUrl = "";

		Integer employeeId = Integer.parseInt(Id);

		// Archive the employee
		Integer noOfRecordArchived = employeeService.archiveEmployee(employeeId);

		// If employeeId not found then throw EmployeeNotFoundException
		if (noOfRecordArchived == 0)
			throw new EmployeeNotFoundException(employeeId.toString());

		logger.info("Employee Having EmployeeId: " + employeeId + " Archived Successfully");

		// Sending the message and message type to the corresponding jsp page
		redir.addFlashAttribute("message", "Employee Archived Successfully");
		redir.addFlashAttribute("messageType", "Success");

		redirectUrl = "/Admin/Employee/showEmployees.abhi";

		view = "redirect:" + redirectUrl;

		return view;
	}

	// UnArchive employee
	@RequestMapping(value = "/unArchiveEmployee.abhi")
	public String unArchiveEmployee(Model model, HttpServletRequest request,
			@RequestParam(name = "employeeId") String Id, RedirectAttributes redir) throws EmployeeNotFoundException {

		String view = "";
		String redirectUrl = "";

		Integer employeeId = Integer.parseInt(Id);

		// UnArchive the employee
		Integer noOfRecordUnArchived = employeeService.unArchiveEmployee(employeeId);

		// If employee not found then throw EmployeeNotFoundException
		if (noOfRecordUnArchived == 0)
			throw new EmployeeNotFoundException(employeeId.toString());

		logger.info("Employee Having EmployeeId: " + employeeId + " UnArchived Successfully");

		// Sending the message and message type to the corresponding jsp page
		redir.addFlashAttribute("message", "Employee UnArchived Successfully");
		redir.addFlashAttribute("messageType", "Success");

		redirectUrl = "/Admin/Employee/showEmployees.abhi";

		view = "redirect:" + redirectUrl;

		return view;
	}
}
