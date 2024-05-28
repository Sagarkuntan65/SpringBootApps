package com.springboot.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Exceptions.ResourceNotFoundException;
import com.springboot.entities.Category;
import com.springboot.payloads.CategoryDto;
import com.springboot.repositories.CategoryRepo;
import com.springboot.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	public ModelMapper mapper;
	

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {

		Category category = this.categoryRepo.save(this.mapper.map(categoryDto, Category.class));
		CategoryDto updatedCategoryDto =this.mapper.map(category, CategoryDto.class);
		return updatedCategoryDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
		Category category = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		Category updatedcategory = this.categoryRepo.save(category);
		return this.mapper.map(updatedcategory, CategoryDto.class);
	}
	
	

	@Override
	public List<CategoryDto> viewAllCategory() {
		List<Category> findAll = this.categoryRepo.findAll();
		List<CategoryDto> categories = findAll.stream().map(category -> this.mapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return categories;
	}

	@Override
	public CategoryDto ViewCategoryById(Integer id) {
		Category category = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		return this.mapper.map(category, CategoryDto.class);
	}

	@Override
	public void DeleteCategory(Integer id) {
		Category category = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		categoryRepo.delete(category);

	}
	
	



}
