package org.avishek.aashayein.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/Common")
public class CommonController {

	// Shows the delete dialog
	@RequestMapping(value = "/showDeleteDialog")
	public String showDeleteDialog(Model model, HttpServletRequest request,
			@RequestParam(required = false) String message) {

		model.addAttribute("message", message);

		return "../dialog/dialogDelete";
	}
}
