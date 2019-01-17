/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.validator;
 * @FileName FileType.java
 * @CreatedDate 11-Jan-2019
 * Modified by @author avishekdas last on 2019-01-11 00:40:21
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
@Constraint(validatedBy = FileTypeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileType {

	String extension() default "jpg|jpeg|JPG|JPEG";

	String message() default "Please upload jpg or jpeg image file";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
