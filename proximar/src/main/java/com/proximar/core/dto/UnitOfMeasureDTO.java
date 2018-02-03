package com.proximar.core.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.proximar.web.validator.annotation.BiggerThenZero;

public class UnitOfMeasureDTO {

	private String productProductCode;

	@NotBlank
	private String uniteName;

	@BiggerThenZero
	private double conversionRate;

	private double cost;

	private double price;

	public String getProductCode() {
		return productProductCode;
	}

	public void setProductCode(String productProductCode) {
		this.productProductCode = productProductCode;
	}

	public String getUniteName() {
		return uniteName;
	}

	public void setUniteName(String uniteName) {
		this.uniteName = uniteName;
	}

	public double getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(double conversionRate) {
		this.conversionRate = conversionRate;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
