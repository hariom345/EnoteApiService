package com.enote.mapper;

import org.mapstruct.Mapper;

import com.enote.dto.FileDetailRequestDto;
import com.enote.dto.FileDetailResponseDto;
import com.enote.model.FileDetailsEntity;

@Mapper(componentModel = "spring")
public interface FileDetailMapper {
	FileDetailsEntity requestDtoToEntity(FileDetailRequestDto fileDetailRequestDto);
	FileDetailResponseDto entityToResponseDto(FileDetailsEntity fileDetailsEntity);

}
