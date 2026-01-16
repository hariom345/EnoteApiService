package com.enote.mapper;
import java.util.List;

import org.apache.commons.io.monitor.FileEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.enote.dto.CategoryResponseDto;
import com.enote.dto.NoteRequestDto;
import com.enote.dto.NoteResponseDto;
import com.enote.model.CategoryEntity;
import com.enote.model.FileDetailsEntity;
import com.enote.model.NoteEntity;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NoteMapper {
	
	NoteEntity requestDtoToEntity(NoteRequestDto noteRequestDto);
	
	NoteResponseDto entityToResponseDto(NoteEntity noteEntity);
	
    List<NoteResponseDto> toResponseDtoList(List<NoteEntity> noteEntityList);
    
    NoteResponseDto.CategorySummary toCategorySummary(CategoryEntity categoryEntity);
    
    NoteResponseDto.FileSummary toFileSummary(FileDetailsEntity fileDetailsEntity);


	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "createdOn", ignore = true)
	@Mapping(target = "updatedBy", ignore = true)
	@Mapping(target = "updatedOn", ignore = true)
	@Mapping(target = "deletedBy", ignore = true)
	@Mapping(target = "deletedOn", ignore = true)
	void updateEntityFromDto(NoteRequestDto requestDto, @MappingTarget NoteEntity entity);
}