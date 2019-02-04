/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.entities;
 * @FileName Employee.java
 * @CreatedDate 28-Jan-2019
 * Modified by @author avishekdas last on 2019-01-28 22:12:59
 */

package org.avishek.aashayein.entities;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TblEmployee")
public class Employee {

	
	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "employeeId", column = @Column(name = "EmployeeId")),
			@AttributeOverride(name = "employeeCode", column = @Column(name = "EmployeeCode")), })
	@GeneratedValue(strategy = GenerationType.AUTO)
	private EmployeeId_Code employeeId_Code;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "MiddleName")
	private String middleName;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "FullName", insertable = false, updatable = false)
	private String fullName;

	@Column(name = "Gender")
	private String gender;

	@Column(name = "MobileNumber")
	private String mobileNumber;

	@Column(name = "AlternateMobileNumber")
	private String alternateMobileNumber;

	@Column(name = "Email")
	private String email;

	@Column(name = "AlternateEmail")
	private String alternateEmail;

	@OneToOne
	@JoinColumn(name = "JobTitleId", referencedColumnName = "JobTitleId")
	private EmployeeTitle title;

	@OneToOne
	@JoinColumn(name = "RoleId", referencedColumnName = "RoleId")
	private EmployeeRole role;

	@Column(name = "Active", insertable = false)
	private Byte active;

	@Column(name = "Archive", insertable = false)
	private Byte archive;

	@Column(name = "Password")
	private String password;

	@Column(name = "ProfilePhoto")
	private String profilePhoto;

	@Column(name = "TokenUUID")
	private String tokenUUID;

	@Column(name = "TokenGeneratedDate")
	private Date tokenGeneratedDate;

	@Column(name = "JoiningDate")
	private Date joiningDate;

	@Column(name = "RecordCreated", nullable = false, updatable = false)
	private Date recordCreated;

	@Column(name = "RecordUpdated", nullable = true, insertable = false)
	private Date recordUpdated;

	public EmployeeId_Code getEmployeeId_Code() {
		return employeeId_Code;
	}

	public void setEmployeeId_Code(EmployeeId_Code employeeId_Code) {
		this.employeeId_Code = employeeId_Code;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public EmployeeTitle getTitle() {
		return title;
	}

	public void setTitle(EmployeeTitle title) {
		this.title = title;
	}

	public EmployeeRole getRole() {
		return role;
	}

	public void setRole(EmployeeRole role) {
		this.role = role;
	}

	public Byte getActive() {
		return active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public Byte getArchive() {
		return archive;
	}

	public void setArchive(Byte archive) {
		this.archive = archive;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public String getTokenUUID() {
		return tokenUUID;
	}

	public void setTokenUUID(String tokenUUID) {
		this.tokenUUID = tokenUUID;
	}

	public Date getTokenGeneratedDate() {
		return tokenGeneratedDate;
	}

	public void setTokenGeneratedDate(Date tokenGeneratedDate) {
		this.tokenGeneratedDate = tokenGeneratedDate;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
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
		return "Employee [employeeId_Code=" + employeeId_Code + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", fullName=" + fullName + ", gender=" + gender
				+ ", mobileNumber=" + mobileNumber + ", alternateMobileNumber=" + alternateMobileNumber + ", email="
				+ email + ", alternateEmail=" + alternateEmail + ", title=" + title + ", role=" + role + ", active="
				+ active + ", archive=" + archive + ", password=" + password + ", profilePhoto=" + profilePhoto
				+ ", tokenUUID=" + tokenUUID + ", tokenGeneratedDate=" + tokenGeneratedDate + ", joiningDate="
				+ joiningDate + ", recordCreated=" + recordCreated + ", recordUpdated=" + recordUpdated + "]";
	}

}
