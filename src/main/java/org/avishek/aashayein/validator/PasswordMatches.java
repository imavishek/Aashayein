package org.avishek.aashayein.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

public @interface PasswordMatches {

	String message() default "Password mismatch";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}