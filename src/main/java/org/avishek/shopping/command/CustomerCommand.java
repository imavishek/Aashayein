/**
 * @Project_Name shopping
 * © @Author avishekdas
 * package org.avishek.shopping.command;
 * @File_Name Customer.java
 * @Created_Date 07-Oct-2018
 * Modified by @author avishekdas last on 2018-10-07 01:32:44
 */

package org.avishek.shopping.command;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class CustomerCommand {
	
	//@Size(min=3, max=25, message="Name must between {min} and {max} characters")
	//@Max(2222)
	@Size(min=3, max=25)
	private String firstName;
	private String lastName;
	
	//@Pattern(regexp="^[56789]\\d{9}$")
	private String mobile;
	
	//@Pattern(regexp="^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$")
	private String email;
	
	//@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{8,16})")
	private String password;
	
	
	private String confirmPassword;
	
	
	private String birthday;
	private String gender;

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
	
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public String toString() {
		return "CustomerCommand [firstName=" + firstName + ", lastName=" + lastName + ", mobile=" + mobile + ", email="
				+ email + ", password=" + password + ", confirmPassword=" + confirmPassword + ", birthday=" + birthday
				+ ", gender=" + gender + "]";
	}

	
	
}
