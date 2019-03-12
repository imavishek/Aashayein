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
import org.avishek.aashayein.command.PasswordCommand;
import org.avishek.aashayein.dto.CountryTO;
import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.exception.EmployeeNotFoundException;
import org.avishek.aashayein.exception.InvalidTokenException;
import org.avishek.aashayein.exception.UploadingFailedException;
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
import org.springframework.web.bind.annotation.RequestParam;
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
		binder.registerCustomEditor(String.class, "addressLine1", new ReplaceSpaceEditor());
		binder.registerCustomEditor(String.class, "addressLine2", new ReplaceSpaceEditor());
		binder.registerCustomEditor(String.class, "password", new ReplaceSpaceEditor());
		binder.registerCustomEditor(String.class, "confirmPassword", new ReplaceSpaceEditor());
	}

	// Shows employee profile
	@RequestMapping(value = "/showEmployeeProfile.abhi")
	public String showEmployeeProfile(Model model, HttpServletRequest request) throws EmployeeNotFoundException {

		String view = "";
		String breadcrumb = "<a href='" + request.getContextPath() + "'>Home /</a> <a href='" + request.getContextPath()
				+ "/EmployeeProfile/showEmployeeProfile.abhi'>" + request.getSession().getAttribute("name").toString()
				+ "</a>";

		EditEmployeeProfileCommand editEmployeeProfileCommand = null;
		List<CountryTO> countries = null;

		// Checking if the employee is logged in or not

		// Getting the logged in employee details
		EmployeeTO employeeTo = employeeService
				.getEmployeeDetailsById(Integer.parseInt(request.getSession().getAttribute("id").toString()));

		// If employee not found then throw EmployeeNotFoundException
		if (employeeTo == null)
			throw new EmployeeNotFoundException("");

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

			// If address is available then set the address details
			if (employeeTo.getAddressId() != null) {
				editEmployeeProfileCommand.setCountry(employeeTo.getCountryId().toString());
				editEmployeeProfileCommand.setState(employeeTo.getStateId().toString());
				editEmployeeProfileCommand.setCity(employeeTo.getCityId().toString());
				editEmployeeProfileCommand.setPinCode(employeeTo.getPostalCode());
				editEmployeeProfileCommand.setAddressLine1(employeeTo.getAddressLine1());
				editEmployeeProfileCommand.setAddressLine2(employeeTo.getAddressLine2());
			}

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
			BindingResult result, HttpServletRequest request, RedirectAttributes redir)
			throws UploadingFailedException {

		/*
		 * To achieve multipart file upload using csrf, 1) You have to register
		 * MultipartFilter before SpringSecurityFilterChain 2) Configure bean of class
		 * CommonsMultipartResolver with bean name filterMultipartResolver 3) Update the
		 * context.xml(Eclipse -> ProjectExplorer -> Server -> Tomcat v8.5.... ->
		 * context.xml) file to <Context allowCasualMultipartParsing="true"></Context>
		 */

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

			// Setting value in Employee Transfer Object
			EmployeeTO employeeTo = new EmployeeTO();

			// Set employeeId from session
			employeeTo.setEmployeeId(Integer.parseInt(request.getSession().getAttribute("id").toString()));

			employeeTo.setFirstName(editEmployeeProfileCommand.getFirstName());
			employeeTo.setMiddleName(editEmployeeProfileCommand.getMiddleName());
			employeeTo.setLastName(editEmployeeProfileCommand.getLastName());
			employeeTo.setGender(editEmployeeProfileCommand.getGender());
			employeeTo.setMobileNumber(editEmployeeProfileCommand.getMobileNumber());
			employeeTo.setAlternateMobileNumber(editEmployeeProfileCommand.getAlternateMobileNumber());
			employeeTo.setAlternateEmail(editEmployeeProfileCommand.getAlternateEmail());
			employeeTo.setCountryId(Integer.parseInt(editEmployeeProfileCommand.getCountry()));
			employeeTo.setStateId(Integer.parseInt(editEmployeeProfileCommand.getState()));
			employeeTo.setCityId(Integer.parseInt(editEmployeeProfileCommand.getCity()));
			employeeTo.setPostalCode(editEmployeeProfileCommand.getPinCode());
			employeeTo.setAddressLine1(editEmployeeProfileCommand.getAddressLine1());
			employeeTo.setAddressLine2(editEmployeeProfileCommand.getAddressLine2());
			employeeTo.setProfilePhotoFile(editEmployeeProfileCommand.getPhoto());

			// Adding the employee
			String message = employeeService.editEmployeeProfile(employeeTo);

			// If error does not occurs then message is empty
			if (message == null) {
				logger.info("Employee Profile Edited Successfully, Employee Code: "
						+ request.getSession().getAttribute("empCode").toString());

				// Sending the message and message type to the corresponding jsp page
				redir.addFlashAttribute("message", "Profile Edited Successfully");
				redir.addFlashAttribute("messageType", "Success");
			} else {
				logger.error("Failed To Edit Employee Profile Having EmployeeCode: "
						+ request.getSession().getAttribute("empCode").toString());
				redir.addFlashAttribute("editEmployeeProfile", editEmployeeProfileCommand);
				redir.addFlashAttribute("message", message);
				redir.addFlashAttribute("messageType", "Error");
			}

			view = "redirect:/EmployeeProfile/showEmployeeProfile.abhi";
		}

		return view;
	}

	// Show setPassword page for employee
	@RequestMapping(value = "/Active/showSetPassword.abhi")
	public String showSetPassword(Model model, HttpServletRequest request,
			@RequestParam(name = "token", required = true) String token) throws InvalidTokenException {

		String view = "";
		// Token expiration time in milliseconds. 24h = 86400000 Milliseconds
		Long expiration = 86400000l;

		// Verify token and expired date
		EmployeeTO employee = employeeService.verifyToken(token, expiration);

		if (employee == null) {
			throw new InvalidTokenException("Invalid Token");
		}

		if (!model.containsAttribute("password")) {
			PasswordCommand password = new PasswordCommand();
			password.setTokenUUID(employee.getTokenUUID());
			model.addAttribute("password", password);
		}

		model.addAttribute("action", "EmployeeProfile/Active/activeAccount.abhi");

		view = "setPassword";

		return view;
	}

	// Setting password to active account
	@RequestMapping(value = "/Active/activeAccount.abhi")
	public String setPassword(Model model, @Valid @ModelAttribute("password") PasswordCommand password,
			BindingResult result, HttpServletRequest request, RedirectAttributes redir)
			throws InvalidTokenException, EmployeeNotFoundException {

		String view = "";

		// Checking data binding error
		if (result.hasErrors()) {

			// Logging DataBinding Error
			for (FieldError error : result.getFieldErrors()) {
				logger.error("Error In DataBinding For Field:- " + error.getField() + " FieldValue:- "
						+ error.getRejectedValue());
			}

			// Redirect to show the error registration page
			redir.addFlashAttribute("password", password);
			redir.addFlashAttribute("org.springframework.validation.BindingResult.password", result);

			view = "redirect:/EmployeeProfile/Active/showSetPassword.abhi?token=" + password.getTokenUUID();
		} else {

			// Token expiration time in milliseconds. 24h = 86400000 Milliseconds
			Long expiration = 86400000l;

			// Verify token and expired date
			EmployeeTO employee = employeeService.verifyToken(password.getTokenUUID(), expiration);

			if (employee == null) {
				throw new InvalidTokenException("Invalid Token");
			}

			// Setting value in Employee Transfer Object
			EmployeeTO employeeTo = new EmployeeTO();

			employeeTo.setEmployeeId(employee.getEmployeeId());
			employeeTo.setEmployeeCode(employee.getEmployeeCode());
			employeeTo.setPassword(password.getPassword());

			// Saving the employee password
			Integer noOfRecordUpdated = employeeService.savePassword(employeeTo);

			// If employeeId not found then throw EmployeeNotFoundException
			if (noOfRecordUpdated == 0)
				throw new EmployeeNotFoundException(employeeTo.getEmployeeCode());

			// Active the employee
			employeeService.activeEmployee(employeeTo.getEmployeeId());

			logger.info("Password Updated Successfully For Employee Having EmployeeId: " + employeeTo.getEmployeeId());
			logger.info(
					"Employee Activated Successfully For Employee Having EmployeeId: " + employeeTo.getEmployeeId());

			view = "redirect:/Login/showLogin.abhi";
		}

		return view;
	}

	// Show Reset Password page for employee
	@RequestMapping(value = "/Reset/showResetPassword.abhi")
	public String showResetPassword(Model model, HttpServletRequest request,
			@RequestParam(name = "token", required = true) String token) throws InvalidTokenException {

		String view = "";
		// Token expiration time in milliseconds. 1h = 3600000 Milliseconds
		Long expiration = 3600000l;

		// Verify token and expired date
		EmployeeTO employee = employeeService.verifyToken(token, expiration);

		if (employee == null) {
			throw new InvalidTokenException("Invalid Token");
		}

		if (!model.containsAttribute("password")) {
			PasswordCommand password = new PasswordCommand();
			password.setTokenUUID(employee.getTokenUUID());
			model.addAttribute("password", password);
		}

		model.addAttribute("action", "EmployeeProfile/Reset/resetPassword.abhi");

		view = "setPassword";

		return view;
	}

	// Saving password
	@RequestMapping(value = "/Reset/resetPassword.abhi")
	public String resetPassword(Model model, @Valid @ModelAttribute("password") PasswordCommand password,
			BindingResult result, HttpServletRequest request, RedirectAttributes redir)
			throws InvalidTokenException, EmployeeNotFoundException {

		String view = "";

		// Checking data binding error
		if (result.hasErrors()) {

			// Logging DataBinding Error
			for (FieldError error : result.getFieldErrors()) {
				logger.error("Error In DataBinding For Field:- " + error.getField() + " FieldValue:- "
						+ error.getRejectedValue());
			}

			// Redirect to show the error registration page
			redir.addFlashAttribute("password", password);
			redir.addFlashAttribute("org.springframework.validation.BindingResult.password", result);

			view = "redirect:/EmployeeProfile/Reset/showResetPassword.abhi?token=" + password.getTokenUUID();
		} else {

			// Token expiration time in milliseconds. 1h = 3600000 Milliseconds
			Long expiration = 3600000l;

			// Verify token and expired date
			EmployeeTO employee = employeeService.verifyToken(password.getTokenUUID(), expiration);

			if (employee == null) {
				throw new InvalidTokenException("Invalid Token");
			}

			// Setting value in Employee Transfer Object
			EmployeeTO employeeTo = new EmployeeTO();

			employeeTo.setEmployeeId(employee.getEmployeeId());
			employeeTo.setEmployeeCode(employee.getEmployeeCode());
			employeeTo.setPassword(password.getPassword());

			// Saving the employee password
			Integer noOfRecordUpdated = employeeService.savePassword(employeeTo);

			// If employeeId not found then throw EmployeeNotFoundException
			if (noOfRecordUpdated == 0)
				throw new EmployeeNotFoundException(employeeTo.getEmployeeCode());

			logger.info("Password Updated Successfully For Employee Having EmployeeId: " + employeeTo.getEmployeeId());

			view = "redirect:/Login/showLogin.abhi";
		}

		return view;
	}

}
