package com.proximar.web.validator.annotation;

import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.proximar.web.validator.MustBeTrueValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MustBeTrueValidator.class)
@Documented
public @interface MustBeTrue {
	
	String message() default "{com.proximar.validator.MustBeTrue.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
}
