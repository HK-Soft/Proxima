package com.proxima.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.proxima.web.validator.annotation.IsUnique;

public class IsUniqueValidator implements ConstraintValidator<IsUnique, Object> {

	@Autowired
	private SessionFactory sessionFactory;

	private IsUnique constraintAnnotation;

	public void initialize(IsUnique constraintAnnotation) {
		this.constraintAnnotation = constraintAnnotation;
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(constraintAnnotation.entityClass());
		Object object = criteria.add(Restrictions.eq(constraintAnnotation.columnName(), value)).uniqueResult();
		session.close();
		return (object == null);
	}

}
