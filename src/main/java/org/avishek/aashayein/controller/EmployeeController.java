package org.avishek.aashayein.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/Employee")
public class EmployeeController {

	private static final Logger logger = LogManager.getLogger(EmployeeController.class);

}
