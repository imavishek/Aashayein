/**
 * @Project_Name shopping
 * © @Author avishekdas
 * package org.avishek.shopping.command;
 * @File_Name Customer.java
 * @Created_Date 07-Oct-2018
 * Modified by @author avishekdas last on 2018-10-07 01:32:44
 */

package org.avishek.shopping.command;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.avishek.shopping.validator.Gender;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.format.annotation.DateTimeFormat;

@ScriptAssert(lang = "javascript", script = "_this.password.equals(_this.confirmPassword)", message = "P")
public class CustomerCommand {
	
	@Pattern(regexp = "^[a-zA-Z\\s]{3,25}$")
	private String firstName;
	
	@Pattern(regexp = "^[a-zA-Z\\s]{2,20}$")
	private String lastName;
	
	@Pattern(regexp = "^[6789]\\d{9}$")
	private String mobile;

	@Pattern(regexp = "^([a-zA-Z\\d_\\-\\.])+\\@([a-zA-Z])+\\.([a-zA-Z]{2,4})")
	private String email;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$")
	private String password;
	
	private String confirmPassword;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	@Past
	private Date birthday;
	
	@Gender(listOfGender = "Male|Female")
	private String gender;
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "CustomerCommand [firstName=" + firstName + ", lastName=" + lastName + ", mobile=" + mobile + ", email="
				+ email + ", password=" + password + ", confirmPassword=" + confirmPassword + ", birthday=" + birthday
				+ ", gender=" + gender + "]";
	}	
}