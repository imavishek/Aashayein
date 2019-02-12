/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.serviceImpl;
 * @FileName AddressServiceImpl.java
 * @CreatedDate 11-Feb-2019
 * Modified by @author avishekdas last on 2019-02-11 22:03:05
 */

package org.avishek.aashayein.serviceImpl;

import java.util.List;

import org.avishek.aashayein.dao.AddressDao;
import org.avishek.aashayein.dto.CityTO;
import org.avishek.aashayein.dto.CountryTO;
import org.avishek.aashayein.dto.StateTO;
import org.avishek.aashayein.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;

	// Getting service provided countries
	@Override
	@Transactional
	public List<CountryTO> getAllActiveCountries() {

		return addressDao.getAllActiveCountries();
	}

	// Getting service provided states
	@Override
	@Transactional
	public List<StateTO> getAllActiveStates(Integer countryId) {

		return addressDao.getAllActiveStates(countryId);
	}

	// Getting service provided cities
	@Override
	@Transactional
	public List<CityTO> getAllActiveCities(Integer stateId) {

		return addressDao.getAllActiveCities(stateId);
	}

}
