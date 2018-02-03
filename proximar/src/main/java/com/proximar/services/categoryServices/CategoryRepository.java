package com.proximar.services.categoryServices;

import org.springframework.data.repository.CrudRepository;

import com.proximar.core.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	public Category findByCode(String code);
}
