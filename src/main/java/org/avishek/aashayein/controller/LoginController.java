package org.avishek.aashayein.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.command.LoginCommand;
import org.avishek.aashayein.dto.EmployeeTO;
import org.avishek.aashayein.propertyEditor.ReplaceSpaceEditor;
import org.avishek.aashayein.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/Login")
public class LoginController {

	@Autowired
	EmployeeService employeeService;

	private static final Logger logger = LogManager.getLogger(EmployeeController.class);

	@InitBinder("login")
	public void customizeBinding(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, "username", new ReplaceSpaceEditor());
		binder.registerCustomEditor(String.class, "password", new ReplaceSpaceEditor());
	}

	// Shows login page
	@RequestMapping(value = "/showLogin.abhi")
	public String showLoginPage(Model model, HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws IOException {

		String view = "";

		if (principal != null) {
			response.sendRedirect(request.getContextPath() + "/home");
		}

		if (!model.containsAttribute("login")) {
			model.addAttribute("login", new LoginCommand());
		}

		view = "login";

		return view;
	}

	// Shows Access Denied page
	@RequestMapping(value = "/showAccessDeniedPage.abhi")
	public String showAccessDeniedPage(Model model, HttpServletRequest request) {

		String view = "";

		view = "accessDenied";

		return view;
	}

	// Do Login
	@RequestMapping(value = "/doLogin.abhi")
	public String doLogin(Model model, @Valid @ModelAttribute("login") LoginCommand login, BindingResult result,
			HttpServletRequest request, RedirectAttributes redir) {

		String view = "";

		// Checking data binding error
		if (result.hasErrors()) {

			// Logging DataBinding Error
			for (FieldError error : result.getFieldErrors()) {
				logger.error("Error In DataBinding For Field:- " + error.getField() + " FieldValue:- "
						+ error.getRejectedValue());
			}

			// Redirect to show the error login page
			redir.addFlashAttribute("login", login);
			redir.addFlashAttribute("org.springframework.validation.BindingResult.login", result);

			view = "redirect:/Login/showLogin.abhi";
		} else {
			System.out.println(login);
			view = "redirect:/home";
		}

		return view;
	}

	// Do Logout
	@RequestMapping(value = "/logout.abhi")
	public String doLogout(Model model, HttpServletRequest request) {

		String view = "";

		view = "redirect:/Login/showLogin.abhi";

		return view;
	}

	// Show username page to active account
	@RequestMapping(value = "/Active/showUsername.abhi")
	public String showUsernameToActive(Model model, HttpServletRequest request) {

		String view = "";

		model.addAttribute("action", "Login/Asyn/Active/activeAccount.abhi");
		model.addAttribute("pageTitle", "Active Account");

		view = "username";

		return view;
	}

	// Send Activation Link To Registered EmailId
	@PostMapping(value = "/Asyn/Active/activeAccount.abhi", headers = "X-Requested-With=XMLHttpRequest")
	@ResponseBody
	public String sendActivationLink(Model model, @RequestParam(required = true) String username,
			HttpServletRequest request) {

		String msg = "";
		EmployeeTO employee = null;

		if (!username.matches("([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})")) {
			msg = "Please Enter Valid Username";

			return msg;
		}

		employee = employeeService.getEmployeeByEmail(username);

		if (employee == null || employee.getArchive().intValue() == 1) {
			msg = "User not found";

			return msg;
		}

		if (employee.getActive().intValue() == 1) {
			msg = "Profile already activated";

			return msg;
		}

		employeeService.sendActivationLink(employee.getEmployeeId());

		msg = "An activation link has been sent to your registered MailId";

		return msg;
	}

	// Show username page to reset password
	@RequestMapping(value = "/Reset/showUsername.abhi")
	public String showUsernameToReset(Model model, HttpServletRequest request) {

		String view = "";

		model.addAttribute("action", "Login/Asyn/Reset/resetPassword.abhi");
		model.addAttribute("pageTitle", "Reset Password");

		view = "username";

		return view;
	}

	// Send Reset Password Link To Registered EmailId
	@PostMapping(value = "/Asyn/Reset/resetPassword.abhi", headers = "X-Requested-With=XMLHttpRequest")
	@ResponseBody
	public String sendResetPasswordLink(Model model, @RequestParam(required = true) String username,
			HttpServletRequest request) {

		String msg = "";
		EmployeeTO employee = null;

		if (!username.matches("([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})")) {
			msg = "Please Enter Valid Username";

			return msg;
		}

		employee = employeeService.getEmployeeByEmail(username);

		if (employee == null || employee.getArchive().intValue() == 1) {
			msg = "User not found";

			return msg;
		}

		if (employee.getActive().intValue() == 0) {
			msg = "Profile not activated";

			return msg;
		}

		employeeService.sendResetPasswordLink(employee.getEmployeeId());

		msg = "A reset link has been sent to your registered MailId";

		return msg;
	}
}
