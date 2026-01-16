package com.enote.service;

import java.util.List;

import com.enote.dto.CategoryRequestDto;
import com.enote.dto.CategoryResponseDto;

public interface CategoryService {
	public  CategoryResponseDto getCategoryById(Integer id);
	public CategoryResponseDto addCategoryDetails(CategoryRequestDto categoryRequestDto);
	public CategoryResponseDto updateCategoryDetails(Integer id,CategoryRequestDto categoryRequestDto);
	public  void deleteCategoryDetails(Integer id);
	public List<CategoryResponseDto> getCategories();

	
	

}
