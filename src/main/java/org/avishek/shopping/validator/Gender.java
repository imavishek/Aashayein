/**
 * @Project_Name Shopping
 * © @Author avishekdas
 * package org.avishek.shopping.validator;
 * @File_Name Gender.java
 * @Created_Date 15-Oct-2018
 * Modified by @author avishekdas last on 2018-10-15 23:03:59
 */

package org.avishek.shopping.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = GenderValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Gender {
	
	String listOfGender() default "Male|Female";
	String message() default "Please choose a Gender";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
