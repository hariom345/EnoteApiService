package com.enote.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.enote.dto.CategoryRequestDto;
import com.enote.dto.CategoryResponseDto;
import com.enote.model.CategoryEntity;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface CategoryMapper {
	@Mapping(target = "status", ignore = true)
	CategoryEntity requestDtoToCategory(CategoryRequestDto categoryRequestDto);
	
	CategoryResponseDto categoryToCategoryResponseDto(CategoryEntity category);
	@Mapping(target = "status", ignore = true)
	void updateCategoryFromDto(CategoryRequestDto categoryRequestDto,@MappingTarget CategoryEntity category);
	
	@Mapping(target = "status", ignore = true)
    List<CategoryResponseDto> toCategoryResponseDtoList(List<CategoryEntity> categoryEntityList);
}
