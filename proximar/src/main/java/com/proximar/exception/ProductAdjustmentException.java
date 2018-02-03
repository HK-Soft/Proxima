package com.proximar.exception;

import org.springframework.validation.BindingResult;

import com.proximar.core.dto.AdjustProductDTO;

@SuppressWarnings("serial")
public class ProductAdjustmentException extends Exception {

	public ProductAdjustmentException(AdjustProductDTO adjustProductDTO, BindingResult bindingResult) {
		// TODO Auto-generated constructor stub
	}

}
