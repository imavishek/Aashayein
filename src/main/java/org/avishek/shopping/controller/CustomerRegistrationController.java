/**
 * @Project_Name shopping
 * © @Author avishekdas
 * package org.avishek.shopping.controller;
 * @File_Name RegistrationController.java
 * @Created_Date 06-Oct-2018
 * Modified by @author avishekdas last on 2018-10-06 19:33:20
 */

package org.avishek.shopping.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.validation.Valid;

import org.avishek.shopping.command.CustomerCommand;
import org.avishek.shopping.propertyEditor.ReplaceSpaceEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/CustomerRegistration")
public class CustomerRegistrationController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, "firstName", new ReplaceSpaceEditor());
		binder.registerCustomEditor(String.class, "lastName", new ReplaceSpaceEditor());
		binder.registerCustomEditor(String.class, "email", new ReplaceSpaceEditor());
	}
	
	/*@ModelAttribute
	public void addingCommonObjects(Model model) {
		model.addAttribute("name", "Common Message");
	}*/
	
	@RequestMapping(value = "/showPage.abhi")
	public String showRegistrationPage(Map<String, CustomerCommand> model) throws ServletException {
		String view = "";
		CustomerCommand customer = new CustomerCommand();
		model.put("customer",customer);
		view = "register";
		
		return view;
	}
	
	@RequestMapping(value = "/register.abhi", method = RequestMethod.POST)
	public String registerCustomer(@Valid @ModelAttribute("customer") CustomerCommand customer, BindingResult result) {
		/*
		@Valid - Whenever performing the databinding task for Customer object that time consider all form validation 
		annotation kept in the Customer.
		*/
		String view = "";
		if(result.hasErrors()) {	//Checking is there any data binding error occurs
			view = "register";
		} else {
			System.out.println(customer);
			view = "";
		}
		
		
		return view;
	}
	
}
