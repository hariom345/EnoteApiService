package com.enote.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.enote.common.EnoteUtil;
import com.enote.dto.CategoryRequestDto;
import com.enote.dto.CategoryResponseDto;
import com.enote.exception.AlreadyExistException;
import com.enote.exception.ResourceNotFoundException;
import com.enote.mapper.CategoryMapper;
import com.enote.model.CategoryEntity;
import com.enote.repository.CategoryRepository;
import com.enote.service.CategoryService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
 private final CategoryRepository categoryRepository;
 private final CategoryMapper categoryMapper;
	
	@Override
	public CategoryResponseDto getCategoryById(Integer id) {
		CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category not found"));
		return categoryMapper.categoryToCategoryResponseDto(categoryEntity);
	}

	@Transactional
	@Override
	public CategoryResponseDto addCategoryDetails(CategoryRequestDto categoryRequestDto) {
		validateCategory(categoryRequestDto,false);
		CategoryEntity categoryEntity = categoryMapper.requestDtoToCategory(categoryRequestDto);
		CategoryEntity savedCategoryEntity = categoryRepository.save(categoryEntity);
		return categoryMapper.categoryToCategoryResponseDto(savedCategoryEntity);
	}

	@Transactional
	@Override
	public CategoryResponseDto updateCategoryDetails(Integer id, CategoryRequestDto categoryRequestDto) {
		validateCategory(categoryRequestDto,false);
        CategoryEntity existingCategoryEntity = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category not found"));
        categoryMapper.updateCategoryFromDto(categoryRequestDto, existingCategoryEntity);
        CategoryEntity updatedCategoryEntity = categoryRepository.save(existingCategoryEntity);
		return categoryMapper.categoryToCategoryResponseDto(updatedCategoryEntity);
	}

	@Transactional
	@Override
	public void deleteCategoryDetails(Integer categoryId) {
		CategoryEntity existingCategoryEntity = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category not found"));
		Integer deleteBy=2;
		categoryRepository.softDeleteById(categoryId, deleteBy);
	}
	
	@Override
	public List<CategoryResponseDto> getCategories() {
		List<CategoryEntity> categoryList = categoryRepository.findAll();
		List<CategoryResponseDto> categoryResponseDtoList = categoryMapper.toCategoryResponseDtoList(categoryList);
		return categoryResponseDtoList;
	}
	
	public void validateCategory(CategoryRequestDto categoryRequestDto, boolean isUpdate) {

		if (EnoteUtil.isNullOrEmpty(categoryRequestDto)) {
			throw new IllegalArgumentException("Category cant not be empty");
		}
		if (EnoteUtil.isNullOrEmpty(categoryRequestDto.getName())) {
			throw new IllegalArgumentException("Category name is required");
		}
		if (EnoteUtil.isNullOrEmpty(categoryRequestDto.getDescription())) {
			throw new IllegalArgumentException("Category description is required");
		}
		Optional<CategoryEntity> exsitingCategoryEntity = categoryRepository.findByName(categoryRequestDto.getName());

		if (isUpdate) {
			if (exsitingCategoryEntity.isPresent()
					&& !exsitingCategoryEntity.get().getId().equals(categoryRequestDto.getId())) {
				throw new AlreadyExistException("Category name already exist");
			}
		} else {
			if (exsitingCategoryEntity.isPresent()) {
				throw new AlreadyExistException("Category name already exist");
			}
		}

	}

	
	
	
	
	
	


	
	
	
	

}
