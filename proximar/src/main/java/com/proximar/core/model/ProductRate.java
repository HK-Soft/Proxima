package com.proximar.core.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.proximar.core.LocalDateTimeConverter;

@Entity
@Table(name = "products_rates")
public class ProductRate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Enumerated(EnumType.STRING)
	@Column(name = "rate_type")
	private ProductRateType productRateType;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "unit_of_measure_id", nullable = false)
	private UnitOfMeasure unitOfMeasure;

	@Column(nullable = false)
	private double rate;

	@Column(nullable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime date;

	public ProductRate() {
	}

	public ProductRate(UnitOfMeasure unitOfMeasure, ProductRateType productRateType, double rate) {
		this.unitOfMeasure = unitOfMeasure;
		this.productRateType = productRateType;
		this.rate = rate;
		this.date = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public ProductRateType getProductRateType() {
		return productRateType;
	}

	public void setProductRateType(ProductRateType productRateType) {
		this.productRateType = productRateType;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
