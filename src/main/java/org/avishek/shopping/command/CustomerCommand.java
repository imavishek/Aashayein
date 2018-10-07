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

public class CustomerCommand {
	
	private String firstName;
	private String lastName;
	private String mobile;
	private String email;
	private String password;
	private String confirmPassword;
	private Date birthday;
	private Date gender;

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
	
	public Date getGender() {
		return gender;
	}

	public void setGender(Date gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	
}
