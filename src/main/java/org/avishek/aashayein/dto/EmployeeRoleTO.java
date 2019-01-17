package org.avishek.aashayein.dto;

import java.util.Date;

public class EmployeeRoleTO {

	private Integer roleId;

	private String roleName;

	private Byte archive;

	private Date recordCreated;

	private Date recordUpdated;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public Byte getArchive() {
		return archive;
	}

	public void setArchive(Byte archive) {
		this.archive = archive;
	}

	@Override
	public String toString() {
		return "EmployeeRoleTO [roleId=" + roleId + ", roleName=" + roleName + "]";
	}

}
