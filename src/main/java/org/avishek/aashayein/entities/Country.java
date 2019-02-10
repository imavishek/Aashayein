/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.controller;
 * @FileName Country.java
 * @CreatedDate 10-Feb-2019
 * Modified by @author avishekdas last on 2019-02-10 21:00:23
 */

package org.avishek.aashayein.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TblCountry")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CountryId")
	private Integer countryId;

	@Column(name = "CountryName")
	private String countryName;

	@Column(name = "IsoCode")
	private String isoCode;

	@Column(name = "IsdCode")
	private String isdCode;

	@Column(name = "Archive", insertable = false)
	private Byte archive;

	@Column(name = "RecordCreated", updatable = false)
	private Date recordCreated;

	@Column(name = "RecordUpdated", insertable = false)
	private Date recordUpdated;

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

	public Byte getArchive() {
		return archive;
	}

	public void setArchive(Byte archive) {
		this.archive = archive;
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
		return "Country [countryId=" + countryId + ", countryName=" + countryName + ", isoCode=" + isoCode
				+ ", isdCode=" + isdCode + ", archive=" + archive + ", recordCreated=" + recordCreated
				+ ", recordUpdated=" + recordUpdated + "]";
	}

}
