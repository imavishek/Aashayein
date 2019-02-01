/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.command;
 * @FileName AddEmployeeRoleCommand.java
 * @CreatedDate 16-Jan-2019
 * Modified by @author avishekdas last on 2019-01-16 02:02:38
 */

package org.avishek.aashayein.command;

import java.util.Arrays;

import javax.validation.constraints.Pattern;

import org.avishek.aashayein.validator.ModuleId;

public class AddEmployeeRoleCommand {

	@Pattern(regexp = "^$|[123456789]\\d{0,1}", message = "Invalid RoleId for edit")
	private String roleId;

	@Pattern(regexp = "[a-zA-Z]{3,25}", message = "Please enter valid RoleName between 3 to 25 character")
	private String roleName;

	@ModuleId(max = 25, message = "Please select valid Permission")
	private String[] moduleIds;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String[] getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(String[] moduleIds) {
		this.moduleIds = moduleIds;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "AddEmployeeRoleCommand [roleId=" + roleId + ", roleName=" + roleName + ", moduleIds="
				+ Arrays.toString(moduleIds) + "]";
	}

}
