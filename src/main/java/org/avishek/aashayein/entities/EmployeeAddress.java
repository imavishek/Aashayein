/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.entities;
 * @FileName EmployeeAddress.java
 * @CreatedDate 10-Feb-2019
 * Modified by @author avishekdas last on 2019-02-10 20:44:11
 */

package org.avishek.aashayein.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TblEmployeeAddress")
public class EmployeeAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AddressId")
	private Integer addressId;

	@OneToOne
	@JoinColumn(name = "CountryId", referencedColumnName = "CountryId")
	private Country country;

	@OneToOne
	@JoinColumn(name = "StateId", referencedColumnName = "StateId")
	private State state;

	@OneToOne
	@JoinColumn(name = "CityId", referencedColumnName = "CityId")
	private City city;

	@Column(name = "PostalCode")
	private String postalCode;

	@Column(name = "AddressLine1")
	private String addressLine1;

	@Column(name = "AddressLine2")
	private String addressLine2;

	@Column(name = "RecordCreated", updatable = false)
	private Date recordCreated;

	@Column(name = "RecordUpdated", insertable = false)
	private Date recordUpdated;

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public Date getRecordCreated() {
		return recordCreated;
	}

	public void setRecordCreated(Date recordCreated) {
		this.recordCreated = recordCreated;
	}

	public Date getRecordUpdated() {
		return recordUpdated;
	}

	public void setRecordUpdated(Date recordUpdated) {
		this.recordUpdated = recordUpdated;
	}

	@Override
	public String toString() {
		return "EmployeeAddress [addressId=" + addressId + ", country=" + country + ", state=" + state + ", city="
				+ city + ", postalCode=" + postalCode + ", addressLine1=" + addressLine1 + ", addressLine2="
				+ addressLine2 + ", recordCreated=" + recordCreated + ", recordUpdated=" + recordUpdated + "]";
	}

}
