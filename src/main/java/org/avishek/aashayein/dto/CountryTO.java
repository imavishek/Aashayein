/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.dto;
 * @FileName CountryTO.java
 * @CreatedDate 11-Feb-2019
 * Modified by @author avishekdas last on 2019-02-11 21:52:51
 */

package org.avishek.aashayein.dto;

public class CountryTO {

	private Integer countryId;

	private String countryName;

	private String isoCode;

	private String isdCode;

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public String getIsdCode() {
		return isdCode;
	}

	public void setIsdCode(String isdCode) {
		this.isdCode = isdCode;
	}

	@Override
	public String toString() {
		return "CountryTO [countryId=" + countryId + ", countryName=" + countryName + ", isoCode=" + isoCode
				+ ", isdCode=" + isdCode + "]";
	}

}
