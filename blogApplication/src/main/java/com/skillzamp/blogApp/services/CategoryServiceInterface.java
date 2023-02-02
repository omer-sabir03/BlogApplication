package com.skillzamp.blogApp.services;

import java.util.List;

import com.skillzamp.blogApp.models.Category;

import payload.CategoryDTO;

public interface CategoryServiceInterface {
		
	CategoryDTO createCategory(CategoryDTO categoryDTO) ;
	CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer Id);
	List<CategoryDTO> getAllCategories();
	CategoryDTO getSingleCategory(Integer Id);
	void deleteCategory(Integer Id);
	
}
