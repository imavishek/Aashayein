/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.controller;
 * @FileName EmployeeTitleController.java
 * @CreatedDate 13-Jan-2019
 * Modified by @author avishekdas last on 2019-01-13 19:47:34
 */

package org.avishek.aashayein.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/EmployeeTitle")
public class EmployeeTitleController {

	private static final Logger logger = LogManager.getLogger(EmployeeRegistrationController.class);

	@RequestMapping(value = "/showTitles")
	public String showEmployeeRoles(Model model, HttpServletRequest request) {
		
		
		String view = "";
		String breadcrumb = "<a href='"+request.getContextPath()+"'>Home</a> / Admin / <a href='"+request.getContextPath()+"/EmployeeTitle/showTitles.abhi'>Employee Titles</a>";
	
		Map<Integer,String> title = new LinkedHashMap<Integer,String>();
		title.put(11, "Admin");
		title.put(22, "SuperAdmin");
		title.put(1, "Sales");
		title.put(4, "Accounting");
		
		model.addAttribute("pageTitle", "Employee Titles");
		model.addAttribute("breadcrumb", breadcrumb);
		model.addAttribute("title", title);
		
		view = "employeeTitle";
		
		return view;
	}
	
	@GetMapping(value = "/addEmployeeTitle")
	@ResponseBody
	public String addEmployeeRole(@RequestParam String titleName) {
		System.out.println(titleName);
		
		return "a";
	}
}
