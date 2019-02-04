package org.avishek.aashayein.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EmployeeId_Code implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer employeeId;

	private String employeeCode;

	public EmployeeId_Code() {
		super();
	}

	public EmployeeId_Code(Integer employeeId, String employeeCode) {
		super();
		this.employeeId = employeeId;
		this.employeeCode = employeeCode;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	@Override
	public String toString() {
		return "EmployeeId_Code [employeeId=" + employeeId + ", employeeCode=" + employeeCode + "]";
	}

}
