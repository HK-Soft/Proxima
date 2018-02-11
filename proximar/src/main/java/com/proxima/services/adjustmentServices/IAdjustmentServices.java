package com.proxima.services.adjustmentServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxima.core.model.Adjustment;

@Service
public class IAdjustmentServices implements AdjustmentServices {

	@Override
	public Adjustment createAdjustment(Adjustment adjustment) {
		return adjustmentRepository.save(adjustment);
	}

	@Autowired
	private AdjustmentRepository adjustmentRepository;

}
