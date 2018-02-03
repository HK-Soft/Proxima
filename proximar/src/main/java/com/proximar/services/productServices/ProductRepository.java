package com.proximar.services.productServices;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.proximar.core.dto.ProductDTO;
import com.proximar.core.model.Category;
import com.proximar.core.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	@Query("Select p.categories FROM Product p WHERE p.code = :code")
	public List<Category> productCategoriesByCode(@Param("code") String code);

	public void deleteProductByCode(String code);

	@Query(nativeQuery = true, name = "getAllStockedProducts")
	public List<ProductDTO> allProducts();

	@Query(nativeQuery = true, name = "getStockedProductByCode")
	public ProductDTO productByCode(@Param("code") String code);

//	@Query("")
//	public UnitOfMeasure addUnitofMeasure(String productCode, String uomCode);

//	@Query(name = "")
//	public UnitOfMeasure deleteUnitofMeasure(String code, UnitOfMeasure uom);

	public void deleteByCode(String code);

}
