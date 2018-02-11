
package com.proxima.services.unitOfMeasureServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxima.core.model.UnitOfMeasure;

@Service
public class IUnitOfMeasureServices implements UnitOfMeasureServices {

	@Override
	public UnitOfMeasure create(UnitOfMeasure unitOfMeasure) {
		return unitOfMeasureRepository.save(unitOfMeasure);
	}

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

}
