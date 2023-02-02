package com.skillzamp.blogApp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillzamp.blogApp.Exception.ResourceNotFoundException;
import com.skillzamp.blogApp.models.Category;
import com.skillzamp.blogApp.repositories.CategoryRepo;
import com.skillzamp.blogApp.services.CategoryServiceInterface;

import payload.CategoryDTO;

@Service
public class CategoryService implements CategoryServiceInterface {
	
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		
		Category category=modelMapper.map(categoryDTO,Category.class);
		 Category savedCategory=  categoryRepo.save(category);
		return modelMapper.map(savedCategory,CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer Id) {
		 Category category= categoryRepo.findById(Id).orElseThrow(()-> new ResourceNotFoundException("Category","Id",Id));
		 category.setCategoryTitle(categoryDTO.getCategoryTitle());
		 category.setCategoryDescription(categoryDTO.getCategoryDescription());
		     Category  updatedCategory=categoryRepo.save(category);
		     
		     CategoryDTO categoryDto=modelMapper.map(updatedCategory,CategoryDTO.class);
		return categoryDto;
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		 List<Category> categories=categoryRepo.findAll();
//	
		List<CategoryDTO> categoriesDTO=categories.stream().map(category->modelMapper.map(category,CategoryDTO.class)).collect(Collectors.toList()); 
		return categoriesDTO;
	}

	@Override
	public CategoryDTO getSingleCategory(Integer Id) {
		Category category=categoryRepo.findById(Id).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",Id));
		
		CategoryDTO categoryDTO=modelMapper.map(category,CategoryDTO.class);
		
		return categoryDTO;
	}

	@Override
	public void deleteCategory(Integer Id) {
		Category category=categoryRepo.findById(Id).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",Id));
				
			categoryRepo.delete(category);
	}

}
