package org.avishek.aashayein.dto;

public class EmployeeRoleTO {

	private Integer roleId;

	private String roleName;

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

	@Override
	public String toString() {
		return "EmployeeRoleTO [roleId=" + roleId + ", roleName=" + roleName + "]";
	}

}
