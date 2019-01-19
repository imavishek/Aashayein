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
@RequestMapping(value = "/EmployeeRole")
public class EmployeeRoleController {

	private static final Logger logger = LogManager.getLogger(EmployeeRoleController.class);

	@Autowired
	EmployeeRoleAndAccessService employeeRoleAndAccessService;

	@RequestMapping(value = "/showRoles")
	public String showEmployeeRoles(Model model, HttpServletRequest request) {

		String view = "";
		String breadcrumb = "<a href='" + request.getContextPath() + "'>Home</a> / Admin / <a href='"
				+ request.getContextPath() + "/EmployeeRole/showRoles.abhi'>Employee Roles</a>";

		List<EmployeeRoleTO> employeeRoles = employeeRoleAndAccessService.getAllRoles();

		model.addAttribute("pageTitle", "Employee Roles");
		model.addAttribute("breadcrumb", breadcrumb);
		model.addAttribute("employeeRoles", employeeRoles);
		// model.addAttribute("message", "d");

		view = "employeeRole";

		return view;
	}

	@RequestMapping(value = "/showEditRole")
	public String showEditEmployeeRoles(Model model, HttpServletRequest request, @RequestParam String roleId)
			throws EmployeeRoleNotFoundException {

		String view = "";
		String breadcrumb = "<a href='" + request.getContextPath() + "'>Home</a> / Admin / <a href='"
				+ request.getContextPath() + "/EmployeeRole/showRoles.abhi'>Employee Roles</a> / <a href='"
				+ request.getContextPath() + "/EmployeeRole/showEditRole.abhi?roleId=" + roleId
				+ "'> Edit Employee Role</a>";

		Integer employeeRoleId = Integer.parseInt(roleId);

		EmployeeRoleTO employeeRole = employeeRoleAndAccessService.getEmployeeRoleById(employeeRoleId);

		if (employeeRole == null)
			throw new EmployeeRoleNotFoundException(employeeRoleId.toString());

		EmployeeRoleAccessTO employeeRoleAccessTO = employeeRoleAndAccessService
				.getModuleAccessByRoleId(employeeRoleId);

		List<EmployeeModuleTO> employeeModules = employeeRoleAndAccessService.getAllModuless();

		AddEmployeeRoleCommand addEmployeeRole = new AddEmployeeRoleCommand();

		addEmployeeRole.setRoleId(employeeRole.getRoleId().toString());
		addEmployeeRole.setRoleName(employeeRole.getRoleName());
		addEmployeeRole.setModuleIds(employeeRoleAccessTO.getModuleIds());

		model.addAttribute("pageTitle", "Edit Employee Role");
		model.addAttribute("breadcrumb", breadcrumb);
		model.addAttribute("addEmployeeRole", addEmployeeRole);
		model.addAttribute("employeeModules", employeeModules);

		view = "addEmployeeRole";

		return view;
	}

	@RequestMapping(value = "/showAddRole")
	public String showAddEmployeeRole(Model model, HttpServletRequest request) {

		String view = "";
		String breadcrumb = "<a href='" + request.getContextPath() + "'>Home</a> / Admin / <a href='"
				+ request.getContextPath() + "/EmployeeRole/showRoles.abhi'>Employee Roles</a> / <a href='"
				+ request.getContextPath() + "/EmployeeRole/showAddRole.abhi'>Add Employee Role</a>";

		List<EmployeeModuleTO> employeeModules = employeeRoleAndAccessService.getAllModuless();

		AddEmployeeRoleCommand addEmployeeRole = new AddEmployeeRoleCommand();

		model.addAttribute("pageTitle", "Add Employee Role");
		model.addAttribute("breadcrumb", breadcrumb);
		model.addAttribute("addEmployeeRole", addEmployeeRole);
		model.addAttribute("employeeModules", employeeModules);

		view = "addEmployeeRole";

		return view;

	}

	@PostMapping(value = "/addEmployeeRole")
	public String addEmployeeRole(Model model,
			@Valid @ModelAttribute("addEmployeeRole") AddEmployeeRoleCommand addEmployeeRole, BindingResult result,
			HttpServletRequest request, RedirectAttributes redir) {

		String view = "";
		String breadcrumb = "";
		String redirectUrl = "";

		if (result.hasErrors()) {

			// Logging DataBinding Error
			for (FieldError error : result.getFieldErrors()) {
				logger.error("Error In DataBinding For Field:- " + error.getField());
			}

			view = "addEmployeeRole";
			breadcrumb = "<a href='" + request.getContextPath() + "'>Home</a> / Admin / <a href='"
					+ request.getContextPath() + "/EmployeeRole/showRoles.abhi'>Employee Roles</a> / <a href='"
					+ request.getContextPath() + "/EmployeeRole/showAddRole.abhi'>Add Employee Role</a>";

			List<EmployeeModuleTO> employeeModules = employeeRoleAndAccessService.getAllModuless();

			model.addAttribute("pageTitle", "Add Employee Role");
			model.addAttribute("breadcrumb", breadcrumb);
			model.addAttribute("addEmployeeRole", addEmployeeRole);
			model.addAttribute("employeeModules", employeeModules);
		} else {

			EmployeeRoleTO employeeRoleTO = new EmployeeRoleTO();
			employeeRoleTO.setRoleName(addEmployeeRole.getRoleName());

			EmployeeRoleAccessTO employeeRoleAccessTO = new EmployeeRoleAccessTO();
			employeeRoleAccessTO.setModuleIds(addEmployeeRole.getModuleIds());

			boolean success = employeeRoleAndAccessService.addEmployeeRoleWithModulePermissions(employeeRoleTO,
					employeeRoleAccessTO);

			if (success) {
				logger.info("Employee Role " + addEmployeeRole.getRoleName() + " Added Successfully");
				redir.addFlashAttribute("message", "Employee Role Added Successfully");
				redir.addFlashAttribute("messageType", "Success");
			} else {
				logger.error("Failed To Add Employee Role " + addEmployeeRole.getRoleName());
				redir.addFlashAttribute("message", "Failed To Add Employee Role");
				redir.addFlashAttribute("messageType", "Error");
			}

			redirectUrl = "/EmployeeRole/showRoles.abhi";

			view = "redirect:" + redirectUrl;
		}

		return view;
	}
}
