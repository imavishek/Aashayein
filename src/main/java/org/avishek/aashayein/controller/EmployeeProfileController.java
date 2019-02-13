/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.controller;
 * @FileName EmployeeProfileController.java
 * @CreatedDate 11-Feb-2019
 * Modified by @author avishekdas last on 2019-02-11 18:57:07
 */

package org.avishek.aashayein.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.command.EditEmployeeProfileCommand;
import org.avishek.aashayein.dto.CountryTO;
import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.propertyEditor.ReplaceSpaceEditor;
import org.avishek.aashayein.service.AddressService;
import org.avishek.aashayein.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/EmployeeProfile")
public class EmployeeProfileController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	AddressService addressService;

	private static final Logger logger = LogManager.getLogger(EmployeeController.class);

	@InitBinder("editEmployeeProfile")
	public void customizeBinding(WebDataBinder binder) {

		binder.registerCustomEditor(String.class, "firstName", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "middleName", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "lastName", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "mobileNumber", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "alternateMobileNumber", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "alternateEmail", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "pinCode", new StringTrimmerEditor(false));
		// binder.registerCustomEditor(String.class, "addressLine1", new
		// StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "addressLine1", new ReplaceSpaceEditor());
		// binder.registerCustomEditor(String.class, "addressLine2", new
		// StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "addressLine2", new ReplaceSpaceEditor());
	}

	// Shows employee profile
	@RequestMapping(value = "/showEmployeeProfile.abhi")
	public String showEmployeeProfile(Model model, HttpServletRequest request) {

		String view = "";
		String breadcrumb = "<a href='" + request.getContextPath() + "'>Home /</a> <a href='" + request.getContextPath()
				+ "/EmployeeProfile/showEmployeeProfile.abhi'>Avishek</a>";

		EditEmployeeProfileCommand editEmployeeProfileCommand = null;
		List<CountryTO> countries = null;

		// Checking if the employee is logged in or not

		// Getting the logged in employee details
		EmployeeTO employeeTo = employeeService.getEmployeeDetailsById(1);

		// Getting service provided countries
		countries = addressService.getAllActiveCountries();

		/*
		 * If error occurs in saveEmployeeProfile controller then redirect to this
		 * controller with EditEmployeeProfileCommand and Binding error as flash
		 * attribute
		 */
		if (!model.containsAttribute("editEmployeeProfile")) {

			// Setting employee profile details in command object
			editEmployeeProfileCommand = new EditEmployeeProfileCommand();

			editEmployeeProfileCommand.setFirstName(employeeTo.getFirstName());
			editEmployeeProfileCommand.setMiddleName(employeeTo.getMiddleName());
			editEmployeeProfileCommand.setLastName(employeeTo.getLastName());
			editEmployeeProfileCommand.setGender(employeeTo.getGender());
			editEmployeeProfileCommand.setMobileNumber(employeeTo.getMobileNumber());
			editEmployeeProfileCommand.setAlternateMobileNumber(employeeTo.getAlternateMobileNumber());
			editEmployeeProfileCommand.setAlternateEmail(employeeTo.getAlternateEmail());
			editEmployeeProfileCommand.setCountry(employeeTo.getCountryId().toString());
			editEmployeeProfileCommand.setState(employeeTo.getStateId().toString());
			editEmployeeProfileCommand.setCity(employeeTo.getCityId().toString());
			editEmployeeProfileCommand.setPinCode(employeeTo.getPostalCode());
			editEmployeeProfileCommand.setAddressLine1(employeeTo.getAddressLine1());
			editEmployeeProfileCommand.setAddressLine2(employeeTo.getAddressLine2());

			model.addAttribute("editEmployeeProfile", editEmployeeProfileCommand);
		}

		model.addAttribute("countries", countries);
		model.addAttribute("employee", employeeTo);
		model.addAttribute("pageTitle", "Profile");
		model.addAttribute("breadcrumb", breadcrumb);

		view = "employeeProfile";

		return view;
	}

	// Edit profile details of employee
	@PostMapping(value = "/saveEmployeeProfile.abhi")
	public String saveEmployeeProfile(Model model,
			@Valid @ModelAttribute("editEmployeeProfile") EditEmployeeProfileCommand editEmployeeProfileCommand,
			BindingResult result, HttpServletRequest request, RedirectAttributes redir) {

		String view = "";

		// Checking data binding error
		if (result.hasErrors()) {

			// Logging DataBinding Error
			for (FieldError error : result.getFieldErrors()) {
				logger.error("Error In DataBinding For Field:- " + error.getField() + " FieldValue:- "
						+ error.getRejectedValue());
			}

			// Redirect to show the error
			redir.addFlashAttribute("editEmployeeProfile", editEmployeeProfileCommand);
			redir.addFlashAttribute("org.springframework.validation.BindingResult.editEmployeeProfile", result);

			view = "redirect:/EmployeeProfile/showEmployeeProfile.abhi";
		} else {

		}

		return view;
	}
}
