package com.proximar.core.dto;

/**
 * 
 * Holds the data of an SQL Query
 * that fetch the rates of a product
 * 
 * */
public class ProductRateDTO {

    private String productCode;

    private String uomCode;

    private double conversionRate;

    private double price;

    public String getProductCode() {
	return productCode;
    }

    public void setProductCode(String productCode) {
	this.productCode = productCode;
    }

    public String getUomCode() {
	return uomCode;
    }

    public void setUomCode(String uomCode) {
	this.uomCode = uomCode;
    }

    public double getConversionRate() {
	return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
	this.conversionRate = conversionRate;
    }

    public double getPrice() {
	return price;
    }

    public void setPrice(double price) {
	this.price = price;
    }

}
