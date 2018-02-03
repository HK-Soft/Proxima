package com.proximar.web.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.proximar.web.validator.IsUniqueValidator;

@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsUniqueValidator.class)
@Documented
public @interface IsUnique {

	String message() default "{com.proximar.validator.IsUnique.message}";

	String columnName();

	Class<?> entityClass();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
