package com.proximar.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.proximar.web.validator.annotation.MustBeTrue;

public class MustBeTrueValidator implements ConstraintValidator<MustBeTrue, Boolean> {

	@Override
	public void initialize(MustBeTrue constraintAnnotation) {
	}

	@Override
	public boolean isValid(Boolean value, ConstraintValidatorContext context) {
		return value;
	}

}
