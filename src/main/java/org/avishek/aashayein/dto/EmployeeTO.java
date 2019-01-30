/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.dto;
 * @FileName EmployeeTO.java
 * @CreatedDate 29-Jan-2019
 * Modified by @author avishekdas last on 2019-01-29 20:51:09
 */

package org.avishek.aashayein.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class EmployeeTO {

	private Integer employeeId;

	private String employeeCode;

	private String firstName;

	private String middleName;

	private String lastName;

	private String gender;

	private String mobileNumber;

	private String alternateMobileNumber;

	private String email;

	private String alternateEmail;

	private Integer jobTitleId;

	private String jobTitleName;

	private Integer roleId;

	private String roleName;

	private Date joiningDate;

	private MultipartFile profilePhotoFile;

	private String profilePhoto;

	private Byte archive;

	private Date recordCreated;

	private Date recordUpdated;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAlternateMobileNumber() {
		return alternateMobileNumber;
	}

	public void setAlternateMobileNumber(String alternateMobileNumber) {
		this.alternateMobileNumber = alternateMobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	public Integer getJobTitleId() {
		return jobTitleId;
	}

	public void setJobTitleId(Integer jobTitleId) {
		this.jobTitleId = jobTitleId;
	}

	public String getJobTitleName() {
		return jobTitleName;
	}

	public void setJobTitleName(String jobTitleName) {
		this.jobTitleName = jobTitleName;
	}

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

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public MultipartFile getProfilePhotoFile() {
		return profilePhotoFile;
	}

	public void setProfilePhotoFile(MultipartFile profilePhotoFile) {
		this.profilePhotoFile = profilePhotoFile;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
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
		return "EmployeeTO [employeeId=" + employeeId + ", employeeCode=" + employeeCode + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName + ", gender=" + gender + ", mobileNumber="
				+ mobileNumber + ", alternateMobileNumber=" + alternateMobileNumber + ", email=" + email
				+ ", alternateEmail=" + alternateEmail + ", jobTitleId=" + jobTitleId + ", jobTitleName=" + jobTitleName
				+ ", roleId=" + roleId + ", roleName=" + roleName + ", joiningDate=" + joiningDate + ", profilePhoto="
				+ profilePhoto + ", archive=" + archive + ", recordCreated=" + recordCreated + ", recordUpdated="
				+ recordUpdated + "]";
	}

}
