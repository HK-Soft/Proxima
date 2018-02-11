package com.proxima.services.rateServices;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.proxima.core.model.Rate;

public interface RateRepository extends CrudRepository<Rate, Long> {

	List<Rate> save(List<Rate> rates);

}
