package org.avishek.aashayein.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ModuleIdValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleId {

	int max() default 2;

	String message() default "Please provide valid data";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
