package com.springboot.services;

import java.util.List;

import com.springboot.payloads.CategoryDto;

public interface CategoryService {
	
	public CategoryDto addCategory(CategoryDto categoryDto);
	
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer id);
	
	public List<CategoryDto> viewAllCategory();
	
	public CategoryDto ViewCategoryById(Integer id);
	
	public void DeleteCategory(Integer id);

}
