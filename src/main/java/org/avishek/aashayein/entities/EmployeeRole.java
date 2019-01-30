package org.avishek.aashayein.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TblEmployeeRole")
public class EmployeeRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RoleId", unique = true, nullable = false)
	private Integer roleId;

	@Column(name = "RoleName", nullable = false, length = 60)
	private String roleName;

	@Column(name = "Archive", nullable = false, insertable = false)
	private Byte archive;

	@Column(name = "RecordCreated", nullable = false, updatable = false)
	private Date recordCreated;

	@Column(name = "RecordUpdated", nullable = true, insertable = false)
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
		return "EmployeeRole [roleId=" + roleId + ", roleName=" + roleName + ", archive=" + archive + ", recordCreated="
				+ recordCreated + ", recordUpdated=" + recordUpdated + "]";
	}

}
