/**
 * @Project_Name shopping
 * © @Author avishekdas
 * package org.avishek.shopping.controller;
 * @File_Name RegistrationController.java
 * @Created_Date 06-Oct-2018
 * Modified by @author avishekdas last on 2018-10-06 19:33:20
 */

package org.avishek.shopping.controller;

import java.util.Map;

import javax.servlet.ServletException;

import org.avishek.shopping.command.CustomerCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(value = "/Customer")
public class CustomerRegistrationController {
	
	@RequestMapping(value = "/showRegistrationPage.abhi")
	public String showRegistrationPage(Map<String, CustomerCommand> model) throws ServletException {
		String view = "";
		CustomerCommand customer = new CustomerCommand();
		model.put("customer",customer);
		view = "register";
		
		return view;
	}
	
	@RequestMapping(value = "/registration.abhi", method = RequestMethod.POST)
	public String registerCustomer(@ModelAttribute("customer") CustomerCommand customer) {
		String view = "";
		System.out.println(customer);
		
		return view;
	}
	
}
