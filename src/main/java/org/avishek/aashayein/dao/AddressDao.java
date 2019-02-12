/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.dao;
 * @FileName AddressDao.java
 * @CreatedDate 11-Feb-2019
 * Modified by @author avishekdas last on 2019-02-11 22:22:58
 */

package org.avishek.aashayein.dao;

import java.util.List;

import org.avishek.aashayein.dto.CityTO;
import org.avishek.aashayein.dto.CountryTO;
import org.avishek.aashayein.dto.StateTO;

public interface AddressDao {

	List<CountryTO> getAllActiveCountries();

	List<StateTO> getAllActiveStates(Integer countryId);

	List<CityTO> getAllActiveCities(Integer stateId);

}
