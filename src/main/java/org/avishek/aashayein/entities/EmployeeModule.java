package org.avishek.aashayein.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TblEmployeeModule")
public class EmployeeModule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ModuleId", unique = true, nullable = false)
	private Integer moduleId;

	@Column(name = "ModuleName", nullable = false, length = 60)
	private String moduleName;

	@Column(name = "ModuleDirectory", nullable = false, length = 60)
	private String moduleDirectory;

	@Column(name = "Archive", nullable = false)
	private Byte archive;

	@Column(name = "RecordCreated", nullable = false, updatable = false)
	private Date recordCreated;

	@Column(name = "RecordUpdated", nullable = true, insertable = false)
	private Date recordUpdated;

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleDirectory() {
		return moduleDirectory;
	}

	public void setModuleDirectory(String moduleDirectory) {
		this.moduleDirectory = moduleDirectory;
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
		return "EmployeeModule [moduleId=" + moduleId + ", moduleName=" + moduleName + ", moduleDirectory="
				+ moduleDirectory + ", archive=" + archive + ", recordCreated=" + recordCreated + ", recordUpdated="
				+ recordUpdated + "]";
	}

}
