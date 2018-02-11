package com.proxima.services.categoryServices;

import com.proxima.core.model.Category;

public interface CategoryServices {

	public Category getCategoryByCode(String code);
	
	public Category saveCategory(Category category);
	
	
}