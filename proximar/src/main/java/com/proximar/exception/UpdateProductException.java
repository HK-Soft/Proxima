package com.proximar.exception;

import org.springframework.validation.BindingResult;

import com.proximar.core.dto.ProductDTO;


@SuppressWarnings("serial")
public class UpdateProductException extends Exception {

	private ProductDTO productDTO;

	private BindingResult bindingResult;

	public UpdateProductException(ProductDTO productDTO, BindingResult bindingResult) {
		this.productDTO = productDTO;
		this.bindingResult = bindingResult;
	}

	public ProductDTO getProductDTO() {
		return productDTO;
	}

	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}

	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}

	public String getMessage() {
		return super.getMessage();
	}

}
