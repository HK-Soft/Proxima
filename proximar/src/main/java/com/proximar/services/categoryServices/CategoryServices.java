package com.proximar.services.categoryServices;

import com.proximar.core.model.Category;

public interface CategoryServices {

	public Category getCategoryByCode(String code);
	
	public Category saveCategory(Category category);
	
	
}