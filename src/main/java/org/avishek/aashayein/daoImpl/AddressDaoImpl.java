/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.daoImpl;
 * @FileName AddressDaoImpl.java
 * @CreatedDate 11-Feb-2019
 * Modified by @author avishekdas last on 2019-02-11 22:23:11
 */

package org.avishek.aashayein.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.avishek.aashayein.dao.AddressDao;
import org.avishek.aashayein.dto.CityTO;
import org.avishek.aashayein.dto.CountryTO;
import org.avishek.aashayein.dto.StateTO;
import org.avishek.aashayein.entities.City;
import org.avishek.aashayein.entities.Country;
import org.avishek.aashayein.entities.State;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoImpl implements AddressDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<CountryTO> getAllActiveCountries() {

		List<CountryTO> countryTo = null;

		String hql = "FROM Country country WHERE country.archive=?1 ORDER BY CountryName ASC";
		Query<Country> query = sessionFactory.getCurrentSession().createQuery(hql, Country.class);
		query.setParameter(1, (byte) 0);
		List<Country> countries = query.list();

		if (!countries.isEmpty()) {
			countryTo = new ArrayList<CountryTO>();

			for (Country country : countries) {
				CountryTO cTo = new CountryTO();

				cTo.setCountryId(country.getCountryId());
				cTo.setCountryName(country.getCountryName());
				cTo.setIsdCode(country.getIsdCode());
				cTo.setIsoCode(country.getIsoCode());

				countryTo.add(cTo);
			}
		}

		return countryTo;
	}

	@Override
	public List<StateTO> getAllActiveStates(Integer countryId) {

		List<StateTO> stateTo = new ArrayList<StateTO>();

		String hql = "FROM State state WHERE state.archive=?1 AND state.countryId=?2 ORDER BY StateName ASC";
		Query<State> query = sessionFactory.getCurrentSession().createQuery(hql, State.class);
		query.setParameter(1, (byte) 0);
		query.setParameter(2, countryId);
		List<State> states = query.list();

		if (!states.isEmpty()) {

			for (State state : states) {
				StateTO sTo = new StateTO();

				sTo.setStateId(state.getStateId());
				sTo.setStateName(state.getStateName());

				stateTo.add(sTo);
			}
		}

		return stateTo;
	}

	@Override
	public List<CityTO> getAllActiveCities(Integer stateId) {

		List<CityTO> cityTo = new ArrayList<CityTO>();

		String hql = "FROM City city WHERE city.archive=?1 AND city.stateId=?2 ORDER BY CityName ASC";
		Query<City> query = sessionFactory.getCurrentSession().createQuery(hql, City.class);
		query.setParameter(1, (byte) 0);
		query.setParameter(2, stateId);
		List<City> cities = query.list();

		if (!cities.isEmpty()) {

			for (City city : cities) {
				CityTO cTo = new CityTO();

				cTo.setCityId(city.getCityId());
				cTo.setCityName(city.getCityName());

				cityTo.add(cTo);
			}
		}

		return cityTo;
	}

}
