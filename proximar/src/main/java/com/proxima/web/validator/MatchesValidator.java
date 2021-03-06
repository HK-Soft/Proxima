package com.proxima.web.validator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.proxima.web.validator.annotation.Matches;

public class MatchesValidator implements ConstraintValidator<Matches, Object> {

	private Matches matches;

	@Override
	public void initialize(Matches constraintAnnotation) {
		matches = constraintAnnotation;
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {

		List<Object> values = new ArrayList<>();

		for (String fieldName : matches.fields()) {
			try {
				Field field = object.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				Object value = field.get(object);
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
