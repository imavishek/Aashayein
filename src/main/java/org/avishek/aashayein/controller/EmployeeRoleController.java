/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.controller;
 * @FileName EmployeeRoleController.java
 * @CreatedDate 13-Jan-2019
 * Modified by @author avishekdas last on 2019-01-13 14:53:43
 */

package org.avishek.aashayein.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.command.AddEmployeeRoleCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/EmployeeRole")
public class EmployeeRoleController {
	
	private static final Logger logger = LogManager.getLogger(EmployeeRegistrationController.class);

	@RequestMapping(value = "/showRoles")
	public String showEmployeeRoles(Model model, HttpServletRequest request) {
		
		
		String view = "";
		String breadcrumb = "<a href='"+request.getContextPath()+"'>Home</a> / Admin / <a href='"+request.getContextPath()+"/EmployeeRole/showRoles.abhi'>Employee Roles</a>";
	
		Map<Integer,String> role = new LinkedHashMap<Integer,String>();
		role.put(11, "Admin");
		role.put(22, "SuperAdmin");
		role.put(1, "Sales");
		role.put(4, "Accounting");
		
		model.addAttribute("pageTitle", "Employee Roles");
		model.addAttribute("breadcrumb", breadcrumb);
		model.addAttribute("role", role);
		
		view = "employeeRole";
		
		return view;
	}
	
	@RequestMapping(value = "/showAddRole")
	public String showAddEmployeeRole(Model model, HttpServletRequest request) {
		
		
		String view = "";
		String breadcrumb = "<a href='"+request.getContextPath()+"'>Home</a> / Admin / <a href='"+request.getContextPath()+"/EmployeeRole/showAddRole.abhi'>Add Employee Role</a>";
	
		Map<Integer,String> module = new LinkedHashMap<Integer,String>();
		module.put(1, "Admin");
		module.put(2, "Sales");
		
		AddEmployeeRoleCommand addEmployeeRole = new AddEmployeeRoleCommand();
		//addEmployeeRole.setmoduleIds("1");
		
		model.addAttribute("pageTitle", "Add Employee Role");
		model.addAttribute("breadcrumb", breadcrumb);
		model.addAttribute("addEmployeeRole", addEmployeeRole);
		model.addAttribute("module", module);
		
		view = "addEmployeeRole";
		
		return view;
	}
	
	
	@PostMapping(value = "/addEmployeeRole")
	public String addEmployeeRole(Model model, @Valid @ModelAttribute("addEmployeeRole") AddEmployeeRoleCommand addEmployeeRole, BindingResult result, HttpServletRequest request) {
		
		String view = "";
		String breadcrumb = "";
		
		Map<Integer,String> module = new LinkedHashMap<Integer,String>();
		module.put(1, "Admin");
		module.put(2, "Sales");
		
		if (result.hasErrors()) {
			
			//Logging DataBinding Error
			for (FieldError error : result.getFieldErrors()) {
				logger.error("Error In DataBinding For Field:- "+ error.getField());
			}
			
			view = "addEmployeeRole";
			breadcrumb = "<a href='"+request.getContextPath()+"'>Home</a> / Admin / <a href='"+request.getContextPath()+"/EmployeeRole/showAddRole.abhi'>Add Employee Role</a>";
			model.addAttribute("addEmployeeRole", addEmployeeRole);
			model.addAttribute("pageTitle", "Add Employee Role");
			model.addAttribute("breadcrumb", breadcrumb);
			model.addAttribute("module", module);
		} else {
			for (Integer modules : addEmployeeRole.getModuleIds()) {
				System.out.println(modules);
			}
			System.out.println(addEmployeeRole);
			view = "";
		}
		
		return view;
	}
}
