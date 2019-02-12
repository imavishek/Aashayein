/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.command;
 * @FileName EditEmployeeProfileCommand.java
 * @CreatedDate 11-Feb-2019
 * Modified by @author avishekdas last on 2019-02-11 20:33:25
 */

package org.avishek.aashayein.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.avishek.aashayein.validator.FileSize;
import org.avishek.aashayein.validator.FileType;
import org.avishek.aashayein.validator.Gender;
import org.springframework.web.multipart.MultipartFile;

public class EditEmployeeProfileCommand {

	@Pattern(regexp = "[a-zA-Z]{3,25}", message = "Please enter valid FirstName between 3 to 25 character")
	private String firstName;

	@Pattern(regexp = "^$|[a-zA-Z]{2,20}", message = "Please enter valid MiddleName between 2 to 20 character")
	private String middleName;

	@Pattern(regexp = "[a-zA-Z]{2,15}", message = "Please enter valid LastName between 2 to 15 character")
	private String lastName;

	@Gender(listOfGender = "Male|Female|Other", message = "Please select Gender")
	private String gender;

	@Pattern(regexp = "[6789]\\d{9}", message = "Please enter valid MobileNumber")
	private String mobileNumber;

	@Pattern(regexp = "^$|[6789]\\d{9}", message = "Please enter valid AlternateMobileNumber")
	private String alternateMobileNumber;

	@Pattern(regexp = "^$|([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})", message = "Please enter valid AlternateEmail")
	private String alternateEmail;

	@NotNull(message = "Please select Country")
	private Integer country;

	@NotNull(message = "Please select State")
	private Integer state;

	@NotNull(message = "Please select City")
	private Integer city;

	@Pattern(regexp = "[123456789]\\d{5}", message = "Please enter valid PinCode")
	private String pinCode;

	@NotNull(message = "Please Enter AddressLine1")
	private String addressLine1;

	private String addressLine2;

	@FileType(extension = "jpg|jpeg|JPG|JPEG", message = "Please upload jpg or jpeg image file")
	@FileSize(size = 1048576, message = "Please upload file under 1Mb")
	private MultipartFile photo;

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

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "EditEmployeeProfileCommand [firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", gender=" + gender + ", mobileNumber=" + mobileNumber + ", alternateMobileNumber="
				+ alternateMobileNumber + ", alternateEmail=" + alternateEmail + ", country=" + country + ", state="
				+ state + ", city=" + city + ", pinCode=" + pinCode + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", photo=" + photo + "]";
	}

}
