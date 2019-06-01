package org.avishek.aashayein.dto;

import java.util.Date;

public class EmployeeRoleAccessTO {

	private String[] moduleIds;

	private Date recordCreated;

	public String[] getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(String[] moduleIds) {
		this.moduleIds = moduleIds;
	}

	public Date getRecordCreated() {
		return recordCreated;
	}

	public void setRecordCreated(Date recordCreated) {
		this.recordCreated = recordCreated;
	}

	@Override
	public String toString() {
		return "EmployeeRoleAccessTO [moduleIds=" + moduleIds + ", recordCreated=" + recordCreated + "]";
	}

}
