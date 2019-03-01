package org.avishek.aashayein.command;

import javax.validation.constraints.Pattern;

import org.avishek.aashayein.validator.PasswordMatches;
import org.hibernate.validator.constraints.Length;

@PasswordMatches(message = "Password Mismatch")
public class PasswordCommand {

	@Length(min = 36, max = 36, message = "Invalid Token")
	private String tokenUUID;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#&])[A-Za-z\\d@$!%*#&]{8,25}$", message = "Please Enter Valid Password")
	private String password;

	// @NotEmpty(message = "Please Enter Valid Confirm Password")
	private String confirmPassword;

	public String getTokenUUID() {
		return tokenUUID;
	}

	public void setTokenUUID(String tokenUUID) {
		this.tokenUUID = tokenUUID;
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

	@Override
	public String toString() {
		return "PasswordCommand [tokenUUID=" + tokenUUID + ", password=" + password + ", confirmPassword="
				+ confirmPassword + "]";
	}
}
