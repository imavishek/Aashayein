/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.validator;
 * @FileName ProfilePicture.java
 * @CreatedDate 10-Jan-2019
 * Modified by @author avishekdas last on 2019-01-10 23:48:08
 */

package org.avishek.aashayein.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = FileSizeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSize {
	
	long size() default 300000;
	String message() default "Please upload file under 300kb";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
