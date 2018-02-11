package com.proxima.services.rateServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxima.core.model.Rate;

@Service
public class IRatesServices implements RateServices {

	@Override
	public Rate saveRate(Rate rate) {
		return rateRepository.save(rate);
	}

	@Autowired
	private RateRepository rateRepository;

	public List<Rate> saveRates(List<Rate> rates) {
		 return (List<Rate>) rateRepository.save(rates);
	}

}
