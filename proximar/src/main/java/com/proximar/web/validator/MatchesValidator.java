package com.proximar.web.validator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.proximar.web.form.RegistrationForm;
import com.proximar.web.validator.annotation.Matches;

public class MatchesValidator implements ConstraintValidator<Matches, RegistrationForm> {

	private Matches matches;

	@Override
	public void initialize(Matches constraintAnnotation) {
		matches = constraintAnnotation;
	}

	@Override
	public boolean isValid(RegistrationForm registrationForm, ConstraintValidatorContext context) {

		List<Object> values = new ArrayList<>();

		for (String fieldName : matches.fields()) {
			try {
				Field field = registrationForm.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				Object value = field.get(registrationForm);
				values.add(value);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		for (Object source : values) {
			for (Object target : values) {
				if (!source.toString().equals(target.toString()))
					return false;
			}
		}

		return true;
	}

}
