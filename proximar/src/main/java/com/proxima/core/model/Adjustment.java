package com.proxima.core.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.proxima.core.LocalDateTimeConverter;

@Entity
@Table(name = "product_adjustments")
public class Adjustment {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column(name = "old_quantity", nullable = false)
	private double oldQuantity;

	@Column(name = "new_quantity", nullable = false)
	private double newQuantity;

	@Column(nullable = true)
	private String description;

	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime adjustmentDate = LocalDateTime.now();

	public Adjustment() {
	}

	public Adjustment(Product product, double oldQuantity, double newQuantity, String description) {
		this.product = product;
		this.oldQuantity = oldQuantity;
		this.newQuantity = newQuantity;
		this.description = description;
		this.adjustmentDate = LocalDateTime.now();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public LocalDateTime getAdjustmentDate() {
		return adjustmentDate;
	}

	public void setAdjustmentDate(LocalDateTime adjustmentDate) {
		this.adjustmentDate = adjustmentDate;
	}

}
