/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.service;
 * @FileName AddressService.java
 * @CreatedDate 11-Feb-2019
 * Modified by @author avishekdas last on 2019-02-11 22:02:46
 */

package org.avishek.aashayein.service;

import java.util.List;

import org.avishek.aashayein.dto.CityTO;
import org.avishek.aashayein.dto.CountryTO;
import org.avishek.aashayein.dto.StateTO;

public interface AddressService {

	List<CountryTO> getAllActiveCountries();

	List<StateTO> getAllActiveStates(Integer countryId);

	List<CityTO> getAllActiveCities(Integer stateID);

}
