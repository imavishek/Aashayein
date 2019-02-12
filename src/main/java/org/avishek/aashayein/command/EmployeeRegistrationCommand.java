/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.command;
 * @FileName EmployeeRegistrationCommand.java
 * @CreatedDate 22-Dec-2018
 * Modified by @author avishekdas last on 2018-12-22 16:26:56
 */

package org.avishek.aashayein.command;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.avishek.aashayein.validator.FileSize;
import org.avishek.aashayein.validator.FileType;
import org.avishek.aashayein.validator.Gender;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class EmployeeRegistrationCommand {

	@Pattern(regexp = "[a-zA-Z]{3,25}", message = "Please enter valid FirstName between 3 to 25 character")
	private String firstName;

	@Pattern(regexp = "^$|[a-zA-Z]{2,20}", message = "Please enter valid MiddleName between 2 to 20 character")
	private String middleName;

	@Pattern(regexp = "[a-zA-Z]{2,15}", message = "Please enter valid LastName between 2 to 15 character")
	private String lastName;

	@Gender(listOfGender = "Male|Female|Other", message = "Please select Gender")
	private String gender;

	@NotNull(message = "Please select Title")
	private Integer title;

	@NotNull(message = "Please select Role")
	private Integer role;

	@Pattern(regexp = "[6789]\\d{9}", message = "Please enter valid MobileNumber")
	private String mobileNumber;

	@Pattern(regexp = "^$|[6789]\\d{9}", message = "Please enter valid AlternateMobileNumber")
	private String alternateMobileNumber;

	@Pattern(regexp = "([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})", message = "Please enter valid Email")
	private String email;

	@Pattern(regexp = "^$|([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})", message = "Please enter valid AlternateEmail")
	private String alternateEmail;

	@NotNull(message = "Please enter Joining Date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@FutureOrPresent(message = "Please enter valid Joining Date")
	private Date joiningDate;

	@FileType(extension = "jpg|jpeg|JPG|JPEG", message = "Please upload jpg or jpeg image file")
	@FileSize(size = 1048576, message = "Please upload file under 1Mb")
	private MultipartFile profilePhoto;

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

	public Integer getTitle() {
		return title;
	}

	public void setTitle(Integer title) {
		this.title = title;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
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

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public MultipartFile getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(MultipartFile profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	@Override
	public String toString() {
		return "EmployeeCommand [firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", gender=" + gender + ", title=" + title + ", role=" + role
				+ ", mobileNumber=" + mobileNumber + ", alternateMobileNumber=" + alternateMobileNumber + ", email="
				+ email + ", alternateEmail=" + alternateEmail + ", joiningDate=" + joiningDate + ", profilePhoto="
				+ profilePhoto + "]";
	}

}
