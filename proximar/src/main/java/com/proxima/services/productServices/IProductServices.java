package com.proxima.services.productServices;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxima.core.dto.AdjustProductDTO;
import com.proxima.core.dto.ProductDTO;
import com.proxima.core.dto.UnitOfMeasureDTO;
import com.proxima.core.model.Adjustment;
import com.proxima.core.model.Category;
import com.proxima.core.model.Document;
import com.proxima.core.model.Product;
import com.proxima.core.model.Rate;
import com.proxima.core.model.UnitOfMeasure;
import com.proxima.services.adjustmentServices.AdjustmentServices;
import com.proxima.services.categoryServices.CategoryServices;
import com.proxima.services.rateServices.RateServices;
import com.proxima.services.unitOfMeasureServices.UnitOfMeasureServices;

@Service
public class IProductServices implements ProductServices {

	@Override
	@Transactional
	public ProductDTO addProduct(ProductDTO productDTO, Document picture) {

		// Basic Unit of measure
		UnitOfMeasure basicUoM = unitOfMeasureServices.create(
				new UnitOfMeasure(productDTO.getBasicUoM().trim(), 1, productDTO.getCost(), productDTO.getPrice()));

		// Product code & description
		Product product = new Product(productDTO.getCode().trim(), productDTO.getDescription().trim(), basicUoM);

		// Product category
		String categoryCode = productDTO.getCategory();
		if ((categoryCode != null) && (!categoryCode.isEmpty())) {
			Category category = categoryServices.getCategoryByCode(categoryCode.trim());

			if (category == null)
				category = categoryServices.saveCategory(new Category(categoryCode.trim()));

			product.setCategory(category);
		}

		// Product re-order level
		product.setReorederLevel(productDTO.getReorderLevel());

		// Product current available quantity in stock
		product.setStartStockLevel(productDTO.getStockLevel());

		product.setPicture(picture);

		product = productRepository.save(product);

		productDTO.setId(product.getId());

		if (picture != null)
			productDTO.setPicture(picture.getCurrentName().concat(".").concat(picture.getType().name()));

		return productDTO;

	}

	@Override
	public ProductDTO updateProduct(String productCode, ProductDTO productDTO, Document picture) {

		Product product = new Product();

		product.setId(productDTO.getId());

		product.setCode(productDTO.getCode());

		// Product code & description
		product.setDescription(productDTO.getDescription());

		// Product category
		String categoryCode = productDTO.getCategory();
		System.out.println("cat " + categoryCode);
		if ((categoryCode != null) && (!categoryCode.isEmpty())) {
			Category category = categoryServices.getCategoryByCode(categoryCode);

			if (category == null)
				category = categoryServices.saveCategory(new Category(categoryCode));
			product.setCategory(category);
		}

		// Product re-order level
		product.setReorederLevel(productDTO.getReorderLevel());

		// Product current available quantity in stock
		product.setStartStockLevel(productDTO.getStartStockLevel());

		UnitOfMeasure basicUoM = new UnitOfMeasure();
		Rate price = rateServices.saveRate(new Rate(productDTO.getPrice()));
		Rate cost = rateServices.saveRate(new Rate(productDTO.getCost()));
		basicUoM.setCode(productDTO.getCode());
		basicUoM.setPrice(price);
		basicUoM.setCost(cost);
		basicUoM.setConversionRate(1);

		product.setBasicUoM(basicUoM);

		product.setPicture(picture);

		productRepository.save(product);

		if (picture != null)
			productDTO.setPicture(picture.getCurrentName().concat(".").concat(picture.getType().name()));

		return productDTO;
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
		Product product = new Product();
		product.setId(adjustProductDTO.getId());
		Adjustment adjustment = new Adjustment(product, adjustProductDTO.getOldQuantity(),
				adjustProductDTO.getNewQuantity(),adjustProductDTO.getDescription());
		adjustmentServices.createAdjustment(adjustment);
		return adjustProductDTO;
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
	private UnitOfMeasureServices unitOfMeasureServices;

	@Autowired
	private AdjustmentServices adjustmentServices;

	@Autowired
	private RateServices rateServices;

}
