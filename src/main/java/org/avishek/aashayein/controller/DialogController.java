package org.avishek.aashayein.controller;

import javax.servlet.http.HttpServletRequest;

import org.avishek.aashayein.command.EmployeeTitleCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/Dialog")
public class DialogController {

	// Shows the delete dialog
	@RequestMapping(value = "/showDeleteDialog")
	public String showDeleteDialog(Model model, HttpServletRequest request,
			@RequestParam(required = false) String message) {

		model.addAttribute("message", message);

		return "../dialog/dialogDelete";
	}

	// Shows the add job title dialog
	@RequestMapping(value = "/showJobTitleDialog")
	public String showJobTitleDialog(Model model, HttpServletRequest request) {

		EmployeeTitleCommand employeeTitle = new EmployeeTitleCommand();

		model.addAttribute("employeeTitle", employeeTitle);

		return "../dialog/dialogAddJobTitle";
	}
}
