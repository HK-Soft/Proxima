package com.proximar.services.rateServices;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.proximar.core.model.ProductRate;

public interface RateRepository extends CrudRepository<ProductRate, Long> {

	List<ProductRate> save(List<ProductRate> rates);

}
