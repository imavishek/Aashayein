/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.entities;
 * @FileName RoleId_ModuleId.java
 * @CreatedDate 16-Jan-2019
 * Modified by @author avishekdas last on 2019-01-16 23:32:24
 */

package org.avishek.aashayein.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleId_ModuleId implements Serializable {

	@Column(name = "RoleId")
	private Integer roleId;

	@Column(name = "ModuleId")
	private Integer moduleId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

}
