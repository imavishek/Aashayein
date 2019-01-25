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
import org.avishek.aashayein.command.EmployeeTitleCommand;
import org.avishek.aashayein.dto.EmployeeTitleTO;
import org.avishek.aashayein.exception.EmployeeTitleNotFoundException;
import org.avishek.aashayein.service.EmployeeTitleService;
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
@RequestMapping(value = "/EmployeeTitle")
public class EmployeeTitleController {

	private static final Logger logger = LogManager.getLogger(EmployeeTitleController.class);

	@Autowired
	EmployeeTitleService employeeTitleService;

	// Shows the list of job title page
	@RequestMapping(value = "/showTitles.abhi")
	public String showEmployeeTitles(Model model, HttpServletRequest request) {

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

	// Shows the add job title dialog
	@RequestMapping(value = "/showJobTitleDialog")
	public String showJobTitleDialog(Model model, HttpServletRequest request) {

		EmployeeTitleCommand employeeTitle = new EmployeeTitleCommand();

		model.addAttribute("employeeTitle", employeeTitle);

		return "../dialog/dialogAddJobTitle";
	}

	// Shows the edit job title dialog
	@RequestMapping(value = "/showEditJobTitleDialog.abhi")
	public String showEditJobTitleDialog(Model model, HttpServletRequest request, @RequestParam String titleId)
			throws EmployeeTitleNotFoundException {

		Integer employeeTitleId = Integer.parseInt(titleId);

		// Checking the existence of the employee title
		EmployeeTitleTO employeeTitleTo = employeeTitleService.getEmployeeTitleById(employeeTitleId);

		// If employee title not found then throw EmployeeTitleNotFoundException
		if (employeeTitleTo == null)
			throw new EmployeeTitleNotFoundException(employeeTitleId.toString());

		EmployeeTitleCommand employeeTitle = new EmployeeTitleCommand();

		// Setting the field value in EmployeeTitleCommand
		employeeTitle.setTitleId(employeeTitleTo.getTitleId().toString());
		employeeTitle.setTitleName(employeeTitleTo.getTitleName());

		model.addAttribute("employeeTitle", employeeTitle);

		return "../dialog/dialogAddJobTitle";
	}

	@PostMapping(value = "/saveEmployeeTitle.abhi")
	public String saveEmployeeTitle(Model model,
			@Valid @ModelAttribute("employeeTitle") EmployeeTitleCommand employeeTitle, BindingResult result,
			HttpServletRequest request, RedirectAttributes redir) throws EmployeeTitleNotFoundException {

		String view = "";
		String redirectUrl = "";
		String message = "";

		// Checking in data binding error
		if (result.hasErrors()) {

			// For showing fields error message in jsp page
			message = "Failed To Add JobTitle <br> Error Message:-";

			// Logging DataBinding Error
			for (FieldError error : result.getFieldErrors()) {
				message = message + "<br>" + error.getDefaultMessage();
				logger.error("Error In DataBinding For Field:- " + error.getField() + ", FieldValue:- "
						+ error.getRejectedValue() + ", Error Message:- " + error.getDefaultMessage());
			}

			redir.addFlashAttribute("message", message);
			redir.addFlashAttribute("messageType", "Error");
		} else {

			// Setting value in EmployeeTitle Transfer Object
			EmployeeTitleTO employeeTitleTo = new EmployeeTitleTO();
			employeeTitleTo.setTitleName(employeeTitle.getTitleName());

			// Add job title if titleId is empty otherwise edit
			if (employeeTitle.getTitleId() == "") {

				// Adding the job tile
				boolean success = employeeTitleService.addEmployeeTitle(employeeTitleTo);

				if (success) {
					logger.info("Employee Title " + employeeTitle.getTitleName() + " Added Successfully");

					// Sending the message and message type to the corresponding jsp page
					redir.addFlashAttribute("message", "Employee Title Added Successfully");
					redir.addFlashAttribute("messageType", "Success");
				} else {
					logger.error("Failed To Add Employee Title " + employeeTitle.getTitleName());
					redir.addFlashAttribute("message", "Failed To Add Employee Title");
					redir.addFlashAttribute("messageType", "Error");
				}
			} else {

				Integer employeeTitleId = Integer.parseInt(employeeTitle.getTitleId());

				// Checking the existence of the employee title
				EmployeeTitleTO title = employeeTitleService.getEmployeeTitleById(employeeTitleId);

				// If employee title not found then throw EmployeeTitleNotFoundException
				if (title == null)
					throw new EmployeeTitleNotFoundException(employeeTitleId.toString());

				employeeTitleTo.setTitleId(employeeTitleId);

				// Edit the employee title
				boolean success = employeeTitleService.editEmployeeTitle(employeeTitleTo);

				if (success) {
					logger.info("Employee Title " + employeeTitle.getTitleName() + " Edited Successfully");

					// Sending the message and message type to the corresponding jsp page
					redir.addFlashAttribute("message", "Employee Title Edited Successfully");
					redir.addFlashAttribute("messageType", "Success");
				} else {
					logger.error("Failed To Edit Employee Title " + employeeTitle.getTitleName());
					redir.addFlashAttribute("message", "Failed To Edit Employee Title");
					redir.addFlashAttribute("messageType", "Error");
				}
			}
		}

		redirectUrl = "/EmployeeTitle/showTitles.abhi";

		view = "redirect:" + redirectUrl;

		return view;
	}

	// Delete employee title
	@RequestMapping(value = "/deleteTitle.abhi")
	public String deleteEmployeeTitle(Model model, HttpServletRequest request, @RequestParam String titleId,
			RedirectAttributes redir) throws EmployeeTitleNotFoundException {

		String view = "";
		String redirectUrl = "";

		Integer employeeTitleId = Integer.parseInt(titleId);

		// Delete the employee title
		Integer noOfRecordDeleted = employeeTitleService.deleteEmployeeTitle(employeeTitleId);

		// If employee title not found then throw EmployeeTitleNotFoundException
		if (noOfRecordDeleted == 0)
			throw new EmployeeTitleNotFoundException(employeeTitleId.toString());

		logger.info("Employee TitleId " + employeeTitleId + " Deleted Successfully");

		// Sending the message and message type to the corresponding jsp page
		redir.addFlashAttribute("message", "Employee Title Deleted Successfully");
		redir.addFlashAttribute("messageType", "Success");

		redirectUrl = "/EmployeeTitle/showTitles.abhi";

		view = "redirect:" + redirectUrl;

		return view;
	}

	// Active employee title
	@RequestMapping(value = "/activeTitle.abhi")
	public String activeEmployeeTitle(Model model, HttpServletRequest request, @RequestParam String titleId,
			RedirectAttributes redir) throws EmployeeTitleNotFoundException {

		String view = "";
		String redirectUrl = "";

		Integer employeeTitleId = Integer.parseInt(titleId);

		// Active the employee title
		Integer noOfRecordUpdated = employeeTitleService.activeEmployeeTitle(employeeTitleId);

		// If employee title not found then throw EmployeeTitleNotFoundException
		if (noOfRecordUpdated == 0)
			throw new EmployeeTitleNotFoundException(employeeTitleId.toString());

		logger.info("Employee TitleId " + employeeTitleId + " Activated Successfully");

		// Sending the message and message type to the corresponding jsp page
		redir.addFlashAttribute("message", "Employee Title Activated Successfully");
		redir.addFlashAttribute("messageType", "Success");

		redirectUrl = "/EmployeeTitle/showTitles.abhi";

		view = "redirect:" + redirectUrl;

		return view;
	}
}
