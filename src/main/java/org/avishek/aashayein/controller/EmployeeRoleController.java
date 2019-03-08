/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.controller;
 * @FileName EmployeeRoleController.java
 * @CreatedDate 13-Jan-2019
 * Modified by @author avishekdas last on 2019-01-13 14:53:43
 */

package org.avishek.aashayein.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.command.AddEmployeeRoleCommand;
import org.avishek.aashayein.dto.EmployeeModuleTO;
import org.avishek.aashayein.dto.EmployeeRoleAccessTO;
import org.avishek.aashayein.dto.EmployeeRoleTO;
import org.avishek.aashayein.exception.EmployeeRoleNotFoundException;
import org.avishek.aashayein.service.EmployeeRoleAndAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/Admin/EmployeeRole")
public class EmployeeRoleController {

	private static final Logger logger = LogManager.getLogger(EmployeeRoleController.class);

	@Autowired
	EmployeeRoleAndAccessService employeeRoleAndAccessService;

	// Shows the list of employee role page
	@RequestMapping(value = "/showRoles.abhi")
	public String showEmployeeRoles(Model model, HttpServletRequest request) {

		String view = "";
		String breadcrumb = "<a href='" + request.getContextPath() + "'>Home</a> / Admin / <a href='"
				+ request.getContextPath() + "/Admin/EmployeeRole/showRoles.abhi'>Employee Roles</a>";

		// Getting all the employee role details
		List<EmployeeRoleTO> employeeRoles = employeeRoleAndAccessService.getAllRoles();

		model.addAttribute("pageTitle", "Employee Roles");
		model.addAttribute("breadcrumb", breadcrumb);
		model.addAttribute("employeeRoles", employeeRoles);

		view = "employeeRole";

		return view;
	}

	// Shows the page to edit employee role
	@RequestMapping(value = "/showEditRole.abhi")
	public String showEditEmployeeRoles(Model model, HttpServletRequest request, @RequestParam String roleId)
			throws EmployeeRoleNotFoundException {

		String view = "";
		String breadcrumb = "<a href='" + request.getContextPath() + "'>Home</a> / Admin / <a href='"
				+ request.getContextPath() + "/Admin/EmployeeRole/showRoles.abhi'>Employee Roles</a> / <a href='"
				+ request.getContextPath() + "/Admin/EmployeeRole/showEditRole.abhi?roleId=" + roleId
				+ "'> Edit Employee Role</a>";

		Integer employeeRoleId = Integer.parseInt(roleId);

		// Checking the existence of the employee role
		EmployeeRoleTO employeeRoleTo = employeeRoleAndAccessService.getEmployeeRoleById(employeeRoleId);

		// If employee role not found then throw EmployeeRoleNotFoundException
		if (employeeRoleTo == null)
			throw new EmployeeRoleNotFoundException(employeeRoleId.toString());

		// Get the modules accessed by the role
		EmployeeRoleAccessTO employeeRoleAccessTO = employeeRoleAndAccessService
				.getModuleAccessByRoleId(employeeRoleId);

		AddEmployeeRoleCommand addEmployeeRole = new AddEmployeeRoleCommand();

		List<EmployeeModuleTO> employeeModules = employeeRoleAndAccessService.getAllModules();

		// Setting the field value in AddEmployeeRoleCommand
		addEmployeeRole.setRoleId(employeeRoleTo.getRoleId().toString());
		addEmployeeRole.setRoleName(employeeRoleTo.getRoleName());
		addEmployeeRole.setModuleIds(employeeRoleAccessTO.getModuleIds());

		model.addAttribute("pageTitle", "Edit Employee Role");
		model.addAttribute("breadcrumb", breadcrumb);
		model.addAttribute("addEmployeeRole", addEmployeeRole);
		model.addAttribute("employeeModules", employeeModules);

		view = "addEmployeeRole";

		return view;
	}

	// Shows the page to add employee role
	@RequestMapping(value = "/showAddRole.abhi")
	public String showAddEmployeeRole(Model model, HttpServletRequest request) {

		String view = "";
		String breadcrumb = "<a href='" + request.getContextPath() + "'>Home</a> / Admin / <a href='"
				+ request.getContextPath() + "/Admin/EmployeeRole/showRoles.abhi'>Employee Roles</a> / <a href='"
				+ request.getContextPath() + "/Admin/EmployeeRole/showAddRole.abhi'>Add Employee Role</a>";

		// Getting all the modules present in application
		List<EmployeeModuleTO> employeeModules = employeeRoleAndAccessService.getAllModules();

		// Creating empty AddEmployeeRoleCommand object
		AddEmployeeRoleCommand addEmployeeRole = new AddEmployeeRoleCommand();

		model.addAttribute("pageTitle", "Add Employee Role");
		model.addAttribute("breadcrumb", breadcrumb);
		model.addAttribute("addEmployeeRole", addEmployeeRole);
		model.addAttribute("employeeModules", employeeModules);

		view = "addEmployeeRole";

		return view;

	}

	// Adding and editing employee role in database
	@PostMapping(value = "/saveEmployeeRole.abhi")
	public String saveEmployeeRole(Model model,
			@Valid @ModelAttribute("addEmployeeRole") AddEmployeeRoleCommand addEmployeeRole, BindingResult result,
			HttpServletRequest request, RedirectAttributes redir) throws EmployeeRoleNotFoundException {

		String view = "";
		String breadcrumb = "";
		String redirectUrl = "";

		// Checking in data binding error
		if (result.hasErrors()) {

			// Logging DataBinding Error
			for (FieldError error : result.getFieldErrors()) {
				logger.error("Error In DataBinding For Field:- " + error.getField() + " FieldValue:- "
						+ error.getRejectedValue());
			}

			// Getting all the modules present in application
			List<EmployeeModuleTO> employeeModules = employeeRoleAndAccessService.getAllModules();

			view = "addEmployeeRole";

			if (addEmployeeRole.getRoleId() == "") {

				breadcrumb = "<a href='" + request.getContextPath() + "'>Home</a> / Admin / <a href='"
						+ request.getContextPath() + "/Admin/EmployeeRole/showRoles.abhi'>Employee Roles</a> / <a href='"
						+ request.getContextPath() + "/Admin/EmployeeRole/showAddRole.abhi'>Add Employee Role</a>";

				model.addAttribute("pageTitle", "Add Employee Role");
			} else {

				breadcrumb = "<a href='" + request.getContextPath() + "'>Home</a> / Admin / <a href='"
						+ request.getContextPath() + "/Admin/EmployeeRole/showRoles.abhi'>Employee Roles</a> / <a href='"
						+ request.getContextPath() + "/Admin/EmployeeRole/showEditRole.abhi?roleId="
						+ addEmployeeRole.getRoleId() + "'> Edit Employee Role</a>";

				model.addAttribute("pageTitle", "Edit Employee Role");
			}

			model.addAttribute("breadcrumb", breadcrumb);
			model.addAttribute("addEmployeeRole", addEmployeeRole);
			model.addAttribute("employeeModules", employeeModules);
		} else {

			// Setting value in EmployeeRole Transfer Object
			EmployeeRoleTO employeeRoleTO = new EmployeeRoleTO();
			employeeRoleTO.setRoleName(addEmployeeRole.getRoleName());

			// Setting value in EmployeeRoleAccess Transfer Object
			EmployeeRoleAccessTO employeeRoleAccessTO = new EmployeeRoleAccessTO();
			employeeRoleAccessTO.setModuleIds(addEmployeeRole.getModuleIds());

			// Add employee role if roleId is empty otherwise edit
			if (addEmployeeRole.getRoleId() == "") {

				// Adding the employee role with modules accessed by the role
				boolean success = employeeRoleAndAccessService.addEmployeeRoleWithModulePermissions(employeeRoleTO,
						employeeRoleAccessTO);

				if (success) {
					logger.info("Employee Role " + addEmployeeRole.getRoleName() + " Added Successfully");

					// Sending the message and message type to the corresponding jsp page
					redir.addFlashAttribute("message", "Employee Role Added Successfully");
					redir.addFlashAttribute("messageType", "Success");
				} else {
					logger.error("Failed To Add Employee Role " + addEmployeeRole.getRoleName());
					redir.addFlashAttribute("message", "Failed To Add Employee Role");
					redir.addFlashAttribute("messageType", "Error");
				}
			} else {

				Integer employeeRoleId = Integer.parseInt(addEmployeeRole.getRoleId());

				// Checking the existence of the employee role
				EmployeeRoleTO employeeRoleTo = employeeRoleAndAccessService.getEmployeeRoleById(employeeRoleId);

				// If employee role not found then throw EmployeeRoleNotFoundException
				if (employeeRoleTo == null)
					throw new EmployeeRoleNotFoundException(employeeRoleId.toString());

				employeeRoleTO.setRoleId(employeeRoleId);

				// Edit the employee role with modules accessed by the role
				boolean success = employeeRoleAndAccessService.editEmployeeRoleWithModulePermissions(employeeRoleTO,
						employeeRoleAccessTO);

				if (success) {
					logger.info("Employee Role " + addEmployeeRole.getRoleName() + " Edited Successfully");

					// Sending the message and message type to the corresponding jsp page
					redir.addFlashAttribute("message", "Employee Role Edited Successfully");
					redir.addFlashAttribute("messageType", "Success");
				} else {
					logger.error("Failed To Edit Employee Role " + addEmployeeRole.getRoleName());
					redir.addFlashAttribute("message", "Failed To Edit Employee Role");
					redir.addFlashAttribute("messageType", "Error");
				}

			}

			redirectUrl = "/Admin/EmployeeRole/showRoles.abhi";

			view = "redirect:" + redirectUrl;
		}

		return view;
	}

	// Delete employee role
	@RequestMapping(value = "/deleteRole.abhi")
	public String deleteEmployeeRole(Model model, HttpServletRequest request, @RequestParam String roleId,
			RedirectAttributes redir) throws EmployeeRoleNotFoundException {

		String view = "";
		String redirectUrl = "";

		Integer employeeRoleId = Integer.parseInt(roleId);

		// Delete the employee role
		Integer noOfRecordDeleted = employeeRoleAndAccessService.deleteEmployeeRole(employeeRoleId);

		// If employee role not found then throw EmployeeRoleNotFoundException
		if (noOfRecordDeleted == 0)
			throw new EmployeeRoleNotFoundException(employeeRoleId.toString());

		logger.info("Employee RoleId " + roleId + " Deleted Successfully");

		// Sending the message and message type to the corresponding jsp page
		redir.addFlashAttribute("message", "Employee Role Deleted Successfully");
		redir.addFlashAttribute("messageType", "Success");

		redirectUrl = "/Admin/EmployeeRole/showRoles.abhi";

		view = "redirect:" + redirectUrl;

		return view;
	}

	// Active employee role
	@RequestMapping(value = "/activeRole.abhi")
	public String activeEmployeeRole(Model model, HttpServletRequest request, @RequestParam String roleId,
			RedirectAttributes redir) throws EmployeeRoleNotFoundException {

		String view = "";
		String redirectUrl = "";

		Integer employeeRoleId = Integer.parseInt(roleId);

		// Active the employee role
		Integer noOfRecordUpdated = employeeRoleAndAccessService.activeEmployeeRole(employeeRoleId);

		// If employee role not found then throw EmployeeRoleNotFoundException
		if (noOfRecordUpdated == 0)
			throw new EmployeeRoleNotFoundException(employeeRoleId.toString());

		logger.info("Employee RoleId " + roleId + " Activated Successfully");

		// Sending the message and message type to the corresponding jsp page
		redir.addFlashAttribute("message", "Employee Role Activated Successfully");
		redir.addFlashAttribute("messageType", "Success");

		redirectUrl = "/Admin/EmployeeRole/showRoles.abhi";

		view = "redirect:" + redirectUrl;

		return view;
	}
}
