package com.proxima.services.productServices;

import java.util.List;

import com.proxima.core.dto.AdjustProductDTO;
import com.proxima.core.dto.ProductDTO;
import com.proxima.core.dto.UnitOfMeasureDTO;
import com.proxima.core.model.Document;

public interface ProductServices {

	public ProductDTO addProduct(ProductDTO productDTO, Document picture);

	public ProductDTO updateProduct(String productCode, ProductDTO productDTO, Document picture);

	public boolean deleteProduct(String productCode);

	public ProductDTO getProductByCode(String productCode);

	public List<ProductDTO> getAllProduct();

	public AdjustProductDTO adjustProduct(AdjustProductDTO adjustProductDTO);

	public UnitOfMeasureDTO addAlternativeUoM(UnitOfMeasureDTO addUnitDTO);

	public UnitOfMeasureDTO updateAlternativeUoM(String productCode, String uomCode, UnitOfMeasureDTO addUnitDTO);

	public boolean deleteAlternativeUoM(String productCode, String uomCode);

	public List<UnitOfMeasureDTO> getAllAlternativeUoM(String productCode);

}