package com.proximar.services.rateServices;

import java.util.List;

import com.proximar.core.model.ProductRate;

public interface RateServices {

	public ProductRate saveRate(ProductRate rate);

	public List<ProductRate> saveRates(List<ProductRate> rates);
}