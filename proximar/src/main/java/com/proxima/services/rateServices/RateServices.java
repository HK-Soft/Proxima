package com.proxima.services.rateServices;

import java.util.List;

import com.proxima.core.model.Rate;

public interface RateServices {

	public Rate saveRate(Rate rate);

	public List<Rate> saveRates(List<Rate> rates);
}