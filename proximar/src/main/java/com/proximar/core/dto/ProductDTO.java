package com.proximar.core.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import com.proximar.core.model.Product;
import com.proximar.web.validator.annotation.BiggerThenZero;
import com.proximar.web.validator.annotation.IsUnique;

public class ProductDTO {

	private long id;

	@NotBlank
	@IsUnique(columnName = "code", entityClass = Product.class)
	private String code;

	private String category;

	@NotBlank
	private String description;

	@NotBlank
	private String basicUoM;

	@BiggerThenZero
	private double price;

	@BiggerThenZero
	private double cost;

	@Min(0)
	private double stockLevel;

	@Min(0)
	private double reorderLevel;

	private String picture;

	public ProductDTO(long id, String code, String description, String category, String basicUoM, double price,
			double cost, double stockLevel, double reorderLevel) {
		this.id = id;
		this.code = code;
		this.description = description;
		this.category = category;
		this.basicUoM = basicUoM;
		this.price = price;
		this.cost = cost;
		this.stockLevel = stockLevel;
		this.reorderLevel = reorderLevel;
	}

	public ProductDTO() {

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBasicUoM() {
		return basicUoM;
	}

	public void setBasicUoM(String basicUoM) {
		this.basicUoM = basicUoM;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getStockLevel() {
		return stockLevel;
	}

	public void setStockLevel(double stockLevel) {
		this.stockLevel = stockLevel;
	}

	public double getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(double reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

}
