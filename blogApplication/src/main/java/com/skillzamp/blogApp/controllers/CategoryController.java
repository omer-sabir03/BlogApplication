package com.skillzamp.blogApp.controllers;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillzamp.blogApp.services.impl.CategoryService;

import javax.validation.Valid;
import payload.ApiResponse;
import payload.CategoryDTO;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/Categories")
	public ResponseEntity<List<CategoryDTO>> getAllCategories(){
		List<CategoryDTO> categories=categoryService.getAllCategories();
		return new ResponseEntity<List<CategoryDTO>>(categories,HttpStatus.OK);
	}
	
	
	@GetMapping("/Categories/{Id}")
	public ResponseEntity<CategoryDTO> getSingleCategory(@PathVariable Integer Id) {
		
		CategoryDTO category=categoryService.getSingleCategory(Id);
		
		return new ResponseEntity<CategoryDTO>(category,HttpStatus.OK);
	}
	
	@PutMapping("/Categories/{Id}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer Id){
		
		CategoryDTO updatedCategory=categoryService.updateCategory(categoryDTO, Id);
		
		return new ResponseEntity<CategoryDTO>(updatedCategory,HttpStatus.OK);
	}
	
	@PostMapping("/Categories")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
		
		CategoryDTO savedCategory=categoryService.createCategory(categoryDTO);
		
		return new ResponseEntity<CategoryDTO>(savedCategory,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/Categories/{Id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer Id) {
		categoryService.deleteCategory(Id);
		return new ResponseEntity<>(new ApiResponse("Category Deleted Succesfully",true),HttpStatus.OK);
	}
}
