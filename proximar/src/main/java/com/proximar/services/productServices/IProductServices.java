package com.proximar.services.productServices;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proximar.core.dto.UnitOfMeasureDTO;
import com.proximar.core.model.Category;
import com.proximar.core.model.Product;
import com.proximar.core.model.ProductRate;
import com.proximar.core.model.ProductRateType;
import com.proximar.core.model.UnitOfMeasure;
import com.proximar.core.dto.AdjustProductDTO;
import com.proximar.core.dto.ProductDTO;
import com.proximar.services.categoryServices.CategoryServices;
import com.proximar.services.rateServices.RateServices;

@Service
public class IProductServices implements ProductServices {

	@Override
	@Transactional
	public ProductDTO addProduct(ProductDTO productDTO) {
		// Basic Unit of measure
		UnitOfMeasure basicUoM = new UnitOfMeasure(productDTO.getBasicUoM().trim(), 1);

		// Product code & description
		Product product = new Product(productDTO.getCode().trim(), productDTO.getDescription().trim(), basicUoM);

		// Product category
		String categoryCode = productDTO.getCategory().trim();
		if ((categoryCode != null) && (!categoryCode.isEmpty())) {
			Category category = categoryServices.getCategoryByCode(categoryCode);

			if (category == null)
				category = categoryServices.saveCategory(new Category(categoryCode));

			product.setCategories(new HashSet<>());
			product.getCategories().add(category);
		}

		// Product re-order level
		double reorderLevel = productDTO.getReorderLevel();
		product.setReorederLevel(reorderLevel);

		// Product current available quantity in stock
		double currentQts = productDTO.getStockLevel();
		product.setStartStockLevel(currentQts);

		product = productRepository.save(product);

		// Product cost
		double cost = productDTO.getCost();
		ProductRate costRate = new ProductRate(product.getBasicUoM(), ProductRateType.cost, cost);
		rateServices.saveRate(costRate);

		// Product price
		double price = productDTO.getPrice();
		ProductRate priceRate = new ProductRate(product.getBasicUoM(), ProductRateType.price, price);
		rateServices.saveRate(priceRate);

		productDTO.setId(product.getId());

		return productDTO;

	}

	@Override
	public ProductDTO updateProduct(String productCode, ProductDTO productDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteProduct(String productCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProductDTO getProductByCode(String productCode) {
		return productRepository.productByCode(productCode);
	}

	@Override
	public List<ProductDTO> getAllProduct() {
		List<ProductDTO> result = productRepository.allProducts();
		if (result == null)
			result = Collections.emptyList();
		return result;
	}

	@Override
	public AdjustProductDTO adjustProduct(AdjustProductDTO adjustProductDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnitOfMeasureDTO addAlternativeUoM(UnitOfMeasureDTO addUnitDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnitOfMeasureDTO updateAlternativeUoM(String productCode, String uomCode, UnitOfMeasureDTO addUnitDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAlternativeUoM(String productCode, String uomCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UnitOfMeasureDTO> getAllAlternativeUoM(String productCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Autowired
	private CategoryServices categoryServices;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private RateServices rateServices;
}
