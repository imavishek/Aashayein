package org.avishek.aashayein.dto;

import java.util.ArrayList;

public class EmployeeRoleAccessTO {

	private ArrayList<Integer> moduleIds;

	public ArrayList<Integer> getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(ArrayList<Integer> moduleIds) {
		this.moduleIds = moduleIds;
	}

	@Override
	public String toString() {
		return "EmployeeRoleAccessTO [moduleIds=" + moduleIds + "]";
	}

}
