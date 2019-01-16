/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.controller;
 * @FileName EmployeeRoleController.java
 * @CreatedDate 13-Jan-2019
 * Modified by @author avishekdas last on 2019-01-13 14:53:43
 */

package org.avishek.aashayein.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.command.AddEmployeeRoleCommand;
import org.avishek.aashayein.dto.EmployeeRoleAccessTO;
import org.avishek.aashayein.dto.EmployeeRoleTO;
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

@Controller
@RequestMapping(value = "/EmployeeRole")
public class EmployeeRoleController {

	private static final Logger logger = LogManager.getLogger(EmployeeRoleController.class);

	@Autowired
	EmployeeRoleAndAccessService employeeRoleAndAccessService;

	@RequestMapping(value = "/showRoles")
	public String showEmployeeRoles(Model model, HttpServletRequest request,
			@RequestParam(required = false) String message) {

		String view = "";
		String breadcrumb = "<a href='" + request.getContextPath() + "'>Home</a> / Admin / <a href='"
				+ request.getContextPath() + "/EmployeeRole/showRoles.abhi'>Employee Roles</a>";

		List<EmployeeRoleTO> employeeRoles = employeeRoleAndAccessService.getAllRoles();

		model.addAttribute("pageTitle", "Employee Roles");
		model.addAttribute("breadcrumb", breadcrumb);
		model.addAttribute("message", message);
		model.addAttribute("employeeRoles", employeeRoles);

		view = "employeeRole";

		return view;
	}

	@RequestMapping(value = "/showAddRole")
	public String showAddEmployeeRole(Model model, HttpServletRequest request) {

		String view = "";
		String breadcrumb = "<a href='" + request.getContextPath() + "'>Home</a> / Admin / <a href='"
				+ request.getContextPath() + "/EmployeeRole/showAddRole.abhi'>Add Employee Role</a>";

		Map<Integer, String> module = new LinkedHashMap<Integer, String>();
		module.put(1, "Admin");
		module.put(2, "Sales");
		module.put(3, "Customer Service");
		module.put(4, "Shipping");
		module.put(5, "Marketing");

		AddEmployeeRoleCommand addEmployeeRole = new AddEmployeeRoleCommand();

		model.addAttribute("pageTitle", "Add Employee Role");
		model.addAttribute("breadcrumb", breadcrumb);
		model.addAttribute("addEmployeeRole", addEmployeeRole);
		model.addAttribute("module", module);

		view = "addEmployeeRole";

		return view;
	}

	@PostMapping(value = "/addEmployeeRole")
	public String addEmployeeRole(Model model,
			@Valid @ModelAttribute("addEmployeeRole") AddEmployeeRoleCommand addEmployeeRole, BindingResult result,
			HttpServletRequest request) {

		String view = "";
		String breadcrumb = "";
		String redirectUrl = "";

		Map<Integer, String> module = new LinkedHashMap<Integer, String>();
		module.put(1, "Admin");
		module.put(2, "Sales");
		module.put(3, "Customer Service");
		module.put(4, "Shipping");
		module.put(5, "Marketing");

		if (result.hasErrors()) {

			// Logging DataBinding Error
			for (FieldError error : result.getFieldErrors()) {
				logger.error("Error In DataBinding For Field:- " + error.getField());
			}

			view = "addEmployeeRole";
			breadcrumb = "<a href='" + request.getContextPath() + "'>Home</a> / Admin / <a href='"
					+ request.getContextPath() + "/EmployeeRole/showAddRole.abhi'>Add Employee Role</a>";
			model.addAttribute("pageTitle", "Add Employee Role");
			model.addAttribute("breadcrumb", breadcrumb);
			model.addAttribute("addEmployeeRole", addEmployeeRole);
			model.addAttribute("module", module);
		} else {

			ArrayList<Integer> moduleIds = new ArrayList<Integer>();
			for (String moduleId : addEmployeeRole.getModuleIds()) {
				moduleIds.add(Integer.parseInt(moduleId));
			}

			EmployeeRoleTO employeeRoleTO = new EmployeeRoleTO();
			employeeRoleTO.setRoleName(addEmployeeRole.getRoleName());

			EmployeeRoleAccessTO employeeRoleAccessTO = new EmployeeRoleAccessTO();
			employeeRoleAccessTO.setModuleIds(moduleIds);

			boolean success = employeeRoleAndAccessService.addEmployeeRoleWithModulePermissions(employeeRoleTO,
					employeeRoleAccessTO);

			if (success) {
				logger.info("Employee Role " + addEmployeeRole.getRoleName() + " Added Successfully");
				model.addAttribute("message", "Employee Role Added Successfully");
			} else {
				model.addAttribute("message", "Error While Adding Employee Role");
			}

			redirectUrl = "/EmployeeRole/showRoles.abhi";

			view = "redirect:" + redirectUrl;
		}

		return view;
	}
}
