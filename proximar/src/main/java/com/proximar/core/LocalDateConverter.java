package com.proximar.core;

import javax.persistence.AttributeConverter;

import java.sql.Date;
import java.time.LocalDate;

/**
 * 
 * A converter from Date to LocalDate Used by Java JPA for date column
 * 
 */
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate attribute) {
	return attribute != null ? Date.valueOf(attribute) : null;
    }

    @Override
    public LocalDate convertToEntityAttribute(Date dbData) {
	return dbData != null ? dbData.toLocalDate() : null;
    }

}