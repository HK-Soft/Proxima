package com.proxima.web.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.proxima.web.validator.BiggerThenZeroValidator;

@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BiggerThenZeroValidator.class)
@Documented
public @interface BiggerThenZero {
    String message() default "{com.proximar.validator.BiggerThenZero.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
