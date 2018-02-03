package com.proximar.services.rateServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proximar.core.model.ProductRate;

@Service
public class IRatesServices implements RateServices {

	@Override
	public ProductRate saveRate(ProductRate rate) {
		return rateRepository.save(rate);
	}

	@Autowired
	private RateRepository rateRepository;

	public List<ProductRate> saveRates(List<ProductRate> rates) {
		 return (List<ProductRate>) rateRepository.save(rates);
	}

}
