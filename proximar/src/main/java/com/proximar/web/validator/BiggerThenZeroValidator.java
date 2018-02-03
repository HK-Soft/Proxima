package com.proximar.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.proximar.web.validator.annotation.BiggerThenZero;

public class BiggerThenZeroValidator implements ConstraintValidator<BiggerThenZero, Double> {

    @Override
    public void initialize(BiggerThenZero constraintAnnotation) {
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
	return (value > 0);
    }

}