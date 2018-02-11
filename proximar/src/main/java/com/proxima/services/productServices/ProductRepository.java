package com.proxima.services.productServices;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proxima.core.dto.ProductDTO;
import com.proxima.core.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public void deleteProductByCode(String code);

	@Query(nativeQuery = true, name = "getAllStockedProducts")
	public List<ProductDTO> allProducts();

	@Query(nativeQuery = true, name = "getStockedProductByCode")
	public ProductDTO productByCode(@Param("code") String code);

	public void deleteByCode(String code);

}
