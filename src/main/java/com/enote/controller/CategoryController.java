package com.enote.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enote.common.ApiConstants;
import com.enote.common.GenericResponse;
import com.enote.common.ResponseUtil;
import com.enote.dto.CategoryRequestDto;
import com.enote.dto.CategoryResponseDto;
import com.enote.service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiConstants.CATEGORY_ROOT_API)
@RequiredArgsConstructor
public class CategoryController {
	private final  CategoryService categoryService;
	
	@GetMapping(ApiConstants.GET_CATEGORY_BY_ID)
	public ResponseEntity<GenericResponse<CategoryResponseDto>> getCategoryById(@PathVariable Integer id) {
	return ResponseUtil.success(categoryService.getCategoryById(id), "Category found Successfully");
	 
	}
	
	@PostMapping(ApiConstants.ADD_CATEGORY_DETAILS)
	public ResponseEntity<GenericResponse<CategoryResponseDto>> addCategoryDetails(@RequestBody @Valid CategoryRequestDto categoryRequestDto ){
    return ResponseUtil.created(categoryService.addCategoryDetails(categoryRequestDto), "Category added Successfully");

	}
	
	@PutMapping(ApiConstants.UPDATE_CATEGORY_DETAILS)
	public ResponseEntity<GenericResponse<CategoryResponseDto>> updateCategoryDetails( @RequestBody @Valid CategoryRequestDto categoryRequestDto,@PathVariable("id")Integer id ){
	 return ResponseUtil.success(categoryService.updateCategoryDetails(id,categoryRequestDto), "Category updated Successfully");

	}
	
	@DeleteMapping(ApiConstants.DELETE_CATEGORY_DETAILS)
	public ResponseEntity<GenericResponse<Void>> deleteCategoryDetails(@PathVariable("id")Integer id ){
	categoryService.deleteCategoryDetails(id);
	 return ResponseUtil.success("Category deleted Successfully");

	}
	
	@GetMapping
	public ResponseEntity<GenericResponse<List<CategoryResponseDto>>> getCategories(){
	return ResponseUtil.success(categoryService.getCategories(), "Category found Successfully");

	}


}
