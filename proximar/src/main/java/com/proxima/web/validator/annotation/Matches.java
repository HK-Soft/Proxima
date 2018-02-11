package com.proxima.web.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.proxima.web.validator.MatchesValidator;


@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MatchesValidator.class)
@Documented
public @interface Matches {
	
	String message() default "{com.proximar.validator.Matches.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String[] fields() default {};

}
