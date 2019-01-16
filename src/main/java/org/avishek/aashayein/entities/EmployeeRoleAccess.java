package org.avishek.aashayein.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TblEmployeeRoleAccess")
public class EmployeeRoleAccess {

	@EmbeddedId
	private RoleId_ModuleId roleId_ModuleId;

	@Column(name = "RecordCreated", nullable = false, updatable = false)
	private Date recordCreated;

	public RoleId_ModuleId getRoleId_ModuleId() {
		return roleId_ModuleId;
	}

	public void setRoleId_ModuleId(RoleId_ModuleId roleId_ModuleId) {
		this.roleId_ModuleId = roleId_ModuleId;
	}

	public Date getRecordCreated() {
		return recordCreated;
	}

	public void setRecordCreated(Date recordCreated) {
		this.recordCreated = recordCreated;
	}

}
