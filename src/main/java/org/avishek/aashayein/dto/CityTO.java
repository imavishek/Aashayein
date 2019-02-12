/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.dto;
 * @FileName CityTO.java
 * @CreatedDate 11-Feb-2019
 * Modified by @author avishekdas last on 2019-02-11 21:53:11
 */

package org.avishek.aashayein.dto;

public class CityTO {

	private Integer cityId;

	private String cityName;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "CityTO [cityId=" + cityId + ", cityName=" + cityName + "]";
	}

}
