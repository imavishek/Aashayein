package org.avishek.aashayein.dto;

public class EmployeeModuleTO {

	private Integer moduleId;

	private String moduleName;

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

	@Override
	public String toString() {
		return "EmployeeModuleTO [moduleId=" + moduleId + ", moduleName=" + moduleName + "]";
	}

}
