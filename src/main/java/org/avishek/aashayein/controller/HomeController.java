/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.controller;
 * @FileName HomeController.java
 * @CreatedDate 19-Jan-2019
 * Modified by @author avishekdas last on 2019-01-19 19:27:27
 */

package org.avishek.aashayein.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = {"/","/home"})
	public String showHomePage(Model model, HttpServletRequest request) {

		return "index";
	}
}
