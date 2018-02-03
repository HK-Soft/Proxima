package com.proximar.core.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "units_of_measure")
public class UnitOfMeasure {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String code;

	@Column(nullable = false)
	private double conversionRate;

	@OneToMany(mappedBy = "unitOfMeasure", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProductRate> rates = new HashSet<>();

	public UnitOfMeasure() {
	}

	public UnitOfMeasure(String code, double conversionRate) {
		this.code = code;
		this.conversionRate = conversionRate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(double conversionRate) {
		this.conversionRate = conversionRate;
	}

	public Set<ProductRate> getRates() {
		return rates;
	}

}
