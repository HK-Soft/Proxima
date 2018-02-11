package com.proxima.services.categoryServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxima.core.model.Category;

@Service
public class ICategoryServices implements CategoryServices {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category getCategoryByCode(String code) {
		return categoryRepository.findByCode(code);
	}

	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

}
