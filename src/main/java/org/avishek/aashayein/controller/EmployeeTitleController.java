/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.controller;
 * @FileName EmployeeTitleController.java
 * @CreatedDate 13-Jan-2019
 * Modified by @author avishekdas last on 2019-01-13 19:47:34
 */

package org.avishek.aashayein.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.command.AddEmployeeRoleCommand;
import org.avishek.aashayein.command.EmployeeTitleCommand;
import org.avishek.aashayein.dto.EmployeeModuleTO;
import org.avishek.aashayein.dto.EmployeeRoleAccessTO;
import org.avishek.aashayein.dto.EmployeeRoleTO;
import org.avishek.aashayein.dto.EmployeeTitleTO;
import org.avishek.aashayein.exception.EmployeeRoleNotFoundException;
import org.avishek.aashayein.service.EmployeeTitleService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/EmployeeTitle")
public class EmployeeTitleController {

	private static final Logger logger = LogManager.getLogger(EmployeeTitleController.class);

	@Autowired
	EmployeeTitleService employeeTitleService;

	// Shows the list of job title page
	@RequestMapping(value = "/showTitles.abhi")
	public String showEmployeeRoles(Model model, HttpServletRequest request) {

		String view = "";
		String breadcrumb = "<a href='" + request.getContextPath() + "'>Home</a> / Admin / <a href='"
				+ request.getContextPath() + "/EmployeeTitle/showTitles.abhi'>Employee Titles</a>";

		// Getting all the job title details
		List<EmployeeTitleTO> jobTitles = employeeTitleService.getAllJobTitles();

		model.addAttribute("pageTitle", "Employee Titles");
		model.addAttribute("breadcrumb", breadcrumb);
		model.addAttribute("jobTitles", jobTitles);

		view = "employeeTitle";

		return view;
	}

	@PostMapping(value = "/saveEmployeeTitle.abhi")
	public String addEmployeeRole(Model model,
			@Valid @ModelAttribute("employeeTitle") EmployeeTitleCommand employeeTitle, BindingResult result,
			HttpServletRequest request, RedirectAttributes redir) {

		String view = "";
		String breadcrumb = "";
		String redirectUrl = "";
		String message = "";

		// Checking in data binding error
		if (result.hasErrors()) {

			message = "Failed To Add JobTitle \r\n Cause:-";
			// Logging DataBinding Error
			for (FieldError error : result.getFieldErrors()) {
				message = message + "\r\n" + error.getDefaultMessage();
				logger.error("Error In DataBinding For Field:- " + error.getField() + ", FieldValue:- "
						+ error.getRejectedValue() + ", Error Message:- " + error.getDefaultMessage());
			}

			redir.addFlashAttribute("message", message);
			redir.addFlashAttribute("messageType", "Error");

			redirectUrl = "/EmployeeTitle/showTitles.abhi";

			view = "redirect:" + redirectUrl;

		} else {

		}

		return view;
	}
}
