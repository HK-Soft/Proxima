package com.proxima.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.proxima.web.validator.annotation.BiggerThenZero;

public class BiggerThenZeroValidator implements ConstraintValidator<BiggerThenZero, Double> {

    @Override
    public void initialize(BiggerThenZero constraintAnnotation) {
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
	return (value > 0);
    }

}