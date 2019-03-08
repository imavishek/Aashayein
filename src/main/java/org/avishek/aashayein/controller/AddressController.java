/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.controller;
 * @FileName AddressController.java
 * @CreatedDate 11-Feb-2019
 * Modified by @author avishekdas last on 2019-02-11 21:50:22
 */

package org.avishek.aashayein.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.avishek.aashayein.dto.CityTO;
import org.avishek.aashayein.dto.CountryTO;
import org.avishek.aashayein.dto.StateTO;
import org.avishek.aashayein.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/Address")
public class AddressController {

	@Autowired
	AddressService addressService;

	// Get service provided countries
	@RequestMapping(value = "/getCountries.abhi")
	public List<CountryTO> getCountries(Model model, HttpServletRequest request) {

		return addressService.getAllActiveCountries();

	}

	// Get service provided states
	@RequestMapping(value = "/Asyn/getStates.abhi", headers = "X-Requested-With=XMLHttpRequest", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StateTO> getStates(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "countryId") String countryId) throws IOException {

		Integer id = Integer.parseInt(countryId);

		return addressService.getAllActiveStates(id);

	}

	// Get service provided cities
	@RequestMapping(value = "/Asyn/getCities.abhi", headers = "X-Requested-With=XMLHttpRequest", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CityTO> getCities(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "stateId") String stateId) throws IOException {

		Integer id = Integer.parseInt(stateId);

		return addressService.getAllActiveCities(id);

	}
}
