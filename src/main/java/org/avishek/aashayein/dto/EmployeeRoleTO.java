package org.avishek.aashayein.dto;

public class EmployeeRoleTO {

	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "EmployeeRoleTO [roleName=" + roleName + "]";
	}

}
