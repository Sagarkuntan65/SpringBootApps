package com.springboot.controllers;

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

import com.springboot.payloads.ApiResponse;
import com.springboot.payloads.CategoryDto;
import com.springboot.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		List<CategoryDto> viewAllCategory = this.categoryService.viewAllCategory();
		return ResponseEntity.ok(viewAllCategory);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int id) {
		CategoryDto viewCategoryById = this.categoryService.ViewCategoryById(id);
		return new ResponseEntity<CategoryDto>(viewCategoryById, HttpStatus.ACCEPTED);
	}

	@PostMapping("/addCategory")
	public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto addCategory = this.categoryService.addCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(addCategory, HttpStatus.CREATED);
	}

	@PutMapping("/addCategory/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer id) {
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, id);
		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id) {
		this.categoryService.DeleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted Successfully", true, id), HttpStatus.OK);
	}

}
