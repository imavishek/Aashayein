/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.command;
 * @FileName AddEmployeeRoleCommand.java
 * @CreatedDate 16-Jan-2019
 * Modified by @author avishekdas last on 2019-01-16 02:02:38
 */

package org.avishek.aashayein.command;

import javax.validation.constraints.Pattern;

public class AddEmployeeRoleCommand {
	
	@Pattern(regexp = "[a-zA-Z]{3,25}", message = "Please enter valid RoleName between 3 to 25 character")
	private String roleName;
	
	//@Pattern(regexp = "^[1-9][0-9]{0,25}$", message = "Please select valid Permission")
	
	private Integer [] moduleIds;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer[] getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(Integer[] moduleIds) {
		this.moduleIds = moduleIds;
	}

	@Override
	public String toString() {
		return "AddEmployeeRoleCommand [roleName=" + roleName + ", moduleIds=" + moduleIds.length + "]";
	}
}
