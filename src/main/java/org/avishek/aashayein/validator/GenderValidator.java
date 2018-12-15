/**
 * @Project_Name Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.validator;
 * @File_Name GenderValidator.java
 * @Created_Date 15-Oct-2018
 * Modified by @author avishekdas last on 2018-10-15 23:06:13
 */

package org.avishek.aashayein.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<Gender, String>  {
	
	private String listOfGender;
	
	@Override
    public void initialize(Gender gender) {
		this.listOfGender = gender.listOfGender();
    }

	@Override
	public boolean isValid(String gender, ConstraintValidatorContext context) {
	
		if (gender == null) {
			return false;
		}
		else if (gender.matches(listOfGender)) {
			return true;
		} else {
			return false;
		}	
	}

}
