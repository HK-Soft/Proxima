package com.proxima.core.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "units_of_measure")
public class UnitOfMeasure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String code;

	@Column(nullable = false)
	private double conversionRate;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "price_id", nullable = false)
	private Rate price;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "cost_id", nullable = false)
	private Rate cost;

	public UnitOfMeasure() {
	}

	public UnitOfMeasure(String code, double conversionRate, double price, double cost) {
		this.code = code;
		this.conversionRate = conversionRate;
		this.price = new Rate(price);
		this.cost = new Rate(cost);
	}

	public long getId() {
		return id;
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

	public Rate getPrice() {
		return price;
	}

	public void setPrice(Rate price) {
		this.price = price;
	}

	public Rate getCost() {
		return cost;
	}

	public void setCost(Rate cost) {
		this.cost = cost;
	}

}
