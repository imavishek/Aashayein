/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.validator;
 * @FileName AddEmployeeRoleValidator.java
 * @CreatedDate 16-Jan-2019
 * Modified by @author avishekdas last on 2019-01-16 02:28:46
 */

package org.avishek.aashayein.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AddEmployeeRoleValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
