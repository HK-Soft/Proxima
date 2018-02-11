package com.proxima.web;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.proxima.ProximaUtils;
import com.proxima.core.dto.AdjustProductDTO;
import com.proxima.core.dto.ProductDTO;
import com.proxima.core.model.Document;
import com.proxima.exception.NewProductException;
import com.proxima.exception.ProductAdjustmentException;
import com.proxima.exception.UpdateProductException;
import com.proxima.services.documentServices.DocumentServices;
import com.proxima.services.productServices.ProductServices;
import com.proxima.storage.StorageException;
import com.proxima.storage.StorageService;

@Controller
public class ProductController {

	private static final Logger logger = Logger.getLogger(ProductController.class.getName());

	private final StorageService storageService;

	@Autowired
	public ProductController(StorageService storageService) {
		this.storageService = storageService;
	}

	// Add new product
	@GetMapping("/produits/nouveau")
	public String showNewProductPage(@ModelAttribute ProductDTO productDTO) {
		productDTO.setCode(ProximaUtils.randomString());
		productDTO.setBasicUoM(messageSource.getMessage(ProximaUtils.DEFAULT_MEASURE_OF_UNIT_KEY, new String[] {},
				LocaleContextHolder.getLocale()));
		return "add-product";
	}

	@PostMapping("/produits/nouveau")
	public String doNewProduct(final Model model, @Valid @ModelAttribute("productDTO") final ProductDTO productDTO,
			final BindingResult bindingResult, @RequestParam("file") MultipartFile file) throws NewProductException {

		if (bindingResult.hasErrors()) {
			logger.warn("Invalid create product form : " + productDTO.toString() + "Reasons : "
					+ bindingResult.getAllErrors());
			return "add-product";
		}

		Document doc = null;
		try {
			doc = storageService.store(file);
		} catch (StorageException ex) {
			logger.warn("Product picture loading " + ex.getMessage());
		}

		try {
			ProductDTO product = productServices.addProduct(productDTO, doc);
			logger.info("Product created successfully " + product.getCode());
			return "redirect:/produits/afficher/" + product.getCode();
		} catch (Exception ex) {
			throw new NewProductException(productDTO, bindingResult);
		}

	}

	// View products
	@GetMapping("/produits")
	public String showProductsPage(final Model model) {
		List<ProductDTO> products = productServices.getAllProduct();
		model.addAttribute("products", products);
		return "products";
	}

	// View product details
	@GetMapping("/produits/afficher/{code}")
	public String showViewProductPage(final Model model, @PathVariable(value = "code") final String code) {

		ProductDTO productDTO = productServices.getProductByCode(code);
		if (productDTO == null)
			return "redirect:/404";

		System.out.println("Show product " + productDTO.getPicture());
		model.addAttribute("product", productDTO);

		return "view-product";
	}

	// Edit product by it code
	@GetMapping("/produits/modifier/{code}")
	public String showEditProductPage(final Model model, @PathVariable(value = "code") final String code) {

		ProductDTO productDTO = productServices.getProductByCode(code);
		if (productDTO == null)
			return "redirect:/404";
		model.addAttribute("productDTO", productDTO);
		return "edit-product";
	}

	@PostMapping("/produits/modifier/{oldCode}")
	public String doEditProduct(final Model model, @PathVariable(value = "oldCode") final String code,
			@Valid @ModelAttribute("productDTO") final ProductDTO productDTO, final BindingResult bindingResult,
			@RequestParam("file") MultipartFile file) throws UpdateProductException {

		if (bindingResult.hasErrors()
				&& bindingResult.getAllErrors().size() != bindingResult.getFieldErrors("code").size()) {
			logger.warn("Invalid update product form : " + productDTO.toString() + "Reasons : "
					+ bindingResult.getAllErrors());
			return "edit-product";
		}

		ProductDTO product = productServices.getProductByCode(code);
		if (product == null)
			return "redirect:/404";

		Document doc = null;
		try {
			String currentName = file.getOriginalFilename().substring(0,
					Math.max(file.getOriginalFilename().length(), file.getOriginalFilename().lastIndexOf('.')));
			doc = documentServices.getByCurrentName(currentName);
			if (doc == null)
				doc = storageService.store(file);
		} catch (StorageException ex) {
			logger.warn("Product picture loading " + ex.getMessage());
		}

		productDTO.setId(product.getId());
		productDTO.setStartStockLevel(product.getStartStockLevel());
		try {
			product = productServices.updateProduct(product.getCode(), productDTO, doc);
			logger.info("Product updated successfully " + product.getCode());
			return "redirect:/produits/afficher/" + product.getCode();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new UpdateProductException(productDTO, bindingResult);
		}

	}

	// Delete a product by it code
	@GetMapping("/produits/delete/{oldCode}")
	public String doDeleteProduct(@PathVariable(value = "oldCode") final String code) {

		ProductDTO product = productServices.getProductByCode(code);
		if (product == null)
			return "redirect:/404";

		productServices.deleteProduct(code);
		// TODO : add message to the model
		return "products";
	}

	// Product adjustment
	@GetMapping("/produits/Ajustement/{code}")
	public String showProductAdjustmentPage(final Model model, @PathVariable(value = "code") final String code) {
		ProductDTO product = productServices.getProductByCode(code);
		if (product == null)
			return "redirect:/404";
		AdjustProductDTO adjustProductDTO = new AdjustProductDTO(product.getCode(), product.getStockLevel(), 0);
		model.addAttribute("adjustProductDTO", adjustProductDTO);
		return "adjust-product";
	}

	@PostMapping("/produits/Ajustement/{code}")
	public String doProductAdjustment(final Model model, @PathVariable(value = "code") final String code,
			@Valid @ModelAttribute("adjustProductDTO") final AdjustProductDTO adjustProductDTO,
			final BindingResult bindingResult) throws ProductAdjustmentException {

		if (bindingResult.hasErrors()) {
			logger.warn("Invalid update product form : " + adjustProductDTO.toString() + "Reasons : "
					+ bindingResult.getAllErrors());
			return "adjust-product";
		}

		ProductDTO product = productServices.getProductByCode(code);

		if (product == null)
			return "redirect:/404";

		adjustProductDTO.setOldQuantity(product.getStockLevel());
		adjustProductDTO.setId(product.getId());

		try {
			productServices.adjustProduct(adjustProductDTO);
			logger.info("Product quantity updated successfully " + adjustProductDTO.getId());
			return "redirect:/produits/afficher/" + product.getCode();
		} catch (Exception ex) {
			System.out.println("Orignal error : " + ex.getMessage());
			throw new ProductAdjustmentException(adjustProductDTO, bindingResult);
		}

	}

	// Product pricing
	@GetMapping("/produits/{code}/unite/modifier")
	public String showProductPricing(final Model model, @PathVariable(value = "code") final String code,
			@ModelAttribute ProductDTO productDTO) {
		ProductDTO product = productServices.getProductByCode(code);
		if (product == null)
			return "redirect:/404";

		model.addAttribute("product", product);
		return "price-product";
	}

	@PostMapping("/produits/{code}/unite/nouveau")
	public String doAddAlternativeMeasurUnit(final Model model, @PathVariable(value = "code") final String code,
			@Valid @ModelAttribute final ProductDTO productDTO, final BindingResult bindingResult) {
		ProductDTO product = productServices.getProductByCode(code);
		if (product == null)
			return "redirect:/404";

		if (bindingResult.hasErrors()) {
			logger.warn("Invalid add new unit form : " + productDTO.toString() + "Reasons : "
					+ bindingResult.getAllErrors());
			model.addAttribute("product", product);
			return "price-product";
		}

		try {
			// productServices.addAlternativeUoM(productDTO);
			return "redirect:/produits/afficher/" + product.getCode();
		} catch (Exception ex) {
			System.out.println("Orignal error : " + ex.getMessage());
		}

		// productServices.
		return "";
	}

	// This controller exception handling
	@ExceptionHandler(NewProductException.class)
	public void newProductExceptionHandler(NewProductException ex) {
		logger.error("Failed registration : " + ex.getMessage());
	}

	@ExceptionHandler(UpdateProductException.class)
	public void updateProductExceptionHandler(UpdateProductException ex) {
		logger.error("Failed update : " + ex.getMessage());
	}

	@ExceptionHandler(ProductAdjustmentException.class)
	public void productAdjustmentExceptionHandler(ProductAdjustmentException ex) {
		logger.error("Faild to update product qunatity : " + ex.getMessage());
	}

	//
	@Autowired
	private ProductServices productServices;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	DocumentServices documentServices;

}
