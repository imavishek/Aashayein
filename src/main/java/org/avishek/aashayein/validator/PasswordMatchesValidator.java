package org.avishek.aashayein.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.avishek.aashayein.command.PasswordCommand;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {

		PasswordCommand passwords = (PasswordCommand) obj;

		Boolean status = passwords.getPassword().equals(passwords.getConfirmPassword());

		if (status == false) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Password Mismatch").addNode("confirmPassword")
					.addConstraintViolation();
		}

		return status;
	}
}