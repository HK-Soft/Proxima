package com.proxima.core.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.proxima.web.validator.annotation.BiggerThenZero;

public class AdjustProductDTO {

	private long id;

	private String productCode;

	private double oldQuantity;

	@BiggerThenZero
	private double newQuantity;

	@NotBlank
	private String description;

	public AdjustProductDTO() {
	}

	public AdjustProductDTO(String productCode, double oldQuantity, double newQuantity) {
		this.productCode = productCode;
		this.oldQuantity = oldQuantity;
		this.newQuantity = newQuantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public double getOldQuantity() {
		return oldQuantity;
	}

	public void setOldQuantity(double oldQuantity) {
		this.oldQuantity = oldQuantity;
	}

	public double getNewQuantity() {
		return newQuantity;
	}

	public void setNewQuantity(double newQuantity) {
		this.newQuantity = newQuantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
