package com.nokieng17.emvcoqr.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = { EmvSpecificationValidator.class })
public @interface EmvSpecification {

	int Id();

	int MaxLength() default 99;

	String message() default "length is exceed!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
