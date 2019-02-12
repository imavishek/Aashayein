/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.entities;
 * @FileName City.java
 * @CreatedDate 10-Feb-2019
 * Modified by @author avishekdas last on 2019-02-10 21:07:19
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
@Table(name = "TblCity")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CityId")
	private Integer cityId;

	@Column(name = "CityName")
	private String cityName;

	@Column(name = "StateId")
	private Integer stateId;

	@Column(name = "Archive", insertable = false)
	private Byte archive;

	@Column(name = "RecordCreated", updatable = false)
	private Date recordCreated;

	@Column(name = "RecordUpdated", insertable = false)
	private Date recordUpdated;

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

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
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
		return "City [CityId=" + cityId + ", cityName=" + cityName + ", stateId=" + stateId + ", archive=" + archive
				+ ", recordCreated=" + recordCreated + ", recordUpdated=" + recordUpdated + "]";
	}

}
