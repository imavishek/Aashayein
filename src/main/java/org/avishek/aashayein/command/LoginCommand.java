package org.avishek.aashayein.command;

import javax.validation.constraints.Pattern;

public class LoginCommand {

	@Pattern(regexp = "([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})", message = "Please enter valid Username")
	private String username;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#&])[A-Za-z\\d@$!%*#&]{8,25}$", message = "Please Enter Valid Password")
	private String password;

	private String rememberMe;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

	@Override
	public String toString() {
		return "LoginCommand [username=" + username + ", password=" + password + ", rememberMe=" + rememberMe + "]";
	}

}
