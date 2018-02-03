package com.proximar.services.productServices;

import java.util.List;

import com.proximar.core.dto.AdjustProductDTO;
import com.proximar.core.dto.ProductDTO;
import com.proximar.core.dto.UnitOfMeasureDTO;

public interface ProductServices {
	
	public ProductDTO addProduct(ProductDTO productDTO);

	public ProductDTO updateProduct(String productCode, ProductDTO productDTO);

	public boolean deleteProduct(String productCode);

	public ProductDTO getProductByCode(String productCode);

	public List<ProductDTO> getAllProduct();

	public AdjustProductDTO adjustProduct(AdjustProductDTO adjustProductDTO);

	public UnitOfMeasureDTO addAlternativeUoM(UnitOfMeasureDTO addUnitDTO);

	public UnitOfMeasureDTO updateAlternativeUoM(String productCode, String uomCode, UnitOfMeasureDTO addUnitDTO);

	public boolean deleteAlternativeUoM(String productCode, String uomCode);

	public List<UnitOfMeasureDTO> getAllAlternativeUoM(String productCode);

}