package org.avishek.aashayein.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/Maintenance")
public class MaintenanceController {

	@RequestMapping(value = "/showMaintenancePage.abhi")
	public String showMaintenancePage() {

		return "maintenance";
	}

}
