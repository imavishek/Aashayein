package org.avishek.aashayein.dto;

import java.util.Date;

public class EmployeeTitleTO {

	private Integer titleId;

	private String titleName;

	private Byte archive;

	private Date recordCreated;

	private Date recordUpdated;

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
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
		return "EmployeeTitleTO [titleId=" + titleId + ", titleName=" + titleName + ", archive=" + archive
				+ ", recordCreated=" + recordCreated + ", recordUpdated=" + recordUpdated + "]";
	}

}
