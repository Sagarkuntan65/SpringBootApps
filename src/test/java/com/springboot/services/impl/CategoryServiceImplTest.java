package com.springboot.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.springboot.entities.Category;
import com.springboot.payloads.CategoryDto;
import com.springboot.repositories.CategoryRepo;

class CategoryServiceImplTest {

    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
  
    @Test
    void testAddCategory() {
        CategoryDto categoryDto = new CategoryDto(); // create a CategoryDto object with test data
        Category category = new Category(); // create a Category object with test data

        when(categoryRepo.save(any(Category.class))).thenReturn(category);
        when(modelMapper.map(any(CategoryDto.class), any())).thenReturn(category);

        CategoryDto result = categoryService.addCategory(categoryDto);

        assertEquals(categoryDto, result); // assert that the returned CategoryDto matches the expected result
    }

    @Test
    void testUpdateCategory() {
        Integer id = 1; // test category ID
        CategoryDto categoryDto = new CategoryDto(); // create a CategoryDto object with test data
        Category category = new Category(); // create a Category object with test data

        when(categoryRepo.findById(id)).thenReturn(Optional.of(category));
        when(categoryRepo.save(any(Category.class))).thenReturn(category);    
        when(modelMapper.map(any(CategoryDto.class), any())).thenReturn(category);

        CategoryDto result = categoryService.updateCategory(categoryDto, id);

        assertEquals(categoryDto, result); // assert that the returned CategoryDto matches the expected result
    }

    @Test
    void testViewAllCategory() {
        Category category1 = new Category(); // create Category objects with test data
        Category category2 = new Category();
        List<Category> categoryList = Arrays.asList(category1, category2);

        when(categoryRepo.findAll()).thenReturn(categoryList);
        when(modelMapper.map(any(Category.class), any())).thenReturn(new CategoryDto());

        List<CategoryDto> result = categoryService.viewAllCategory();

        assertEquals(2, result.size()); // assert that the returned list size matches the expected size
    }

    @Test
    void testViewCategoryById() {
        Integer id = 1; // test category ID
        Category category = new Category(); // create a Category object with test data

        when(categoryRepo.findById(id)).thenReturn(Optional.of(category));
        when(modelMapper.map(any(Category.class), any())).thenReturn(new CategoryDto());

        CategoryDto result = categoryService.ViewCategoryById(id);

        assertEquals(new CategoryDto(), result); // assert that the returned CategoryDto matches the expected result
    }
    
//    @Test
//    void NewCategory() {
//    	Category category=new Category();
//    	categoryService.NewCategory();
//    }
    
  
//    @Test
//    void NewCategoryForELse() {
//    	Category category=new Category();
//    	category.setId(0);
//    	categoryService.NewCategory();
//    }

    @Test
    void testDeleteCategory() {
        Integer id = 1; // test category ID
        Category category = new Category(); // create a Category object with test data

        when(categoryRepo.findById(id)).thenReturn(Optional.of(category));

        categoryService.DeleteCategory(id);

        // Verify that the delete method is called once
        verify(categoryRepo).delete(category);
    }
}
