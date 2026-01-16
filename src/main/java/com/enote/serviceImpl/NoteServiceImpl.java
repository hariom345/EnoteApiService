package com.enote.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.enote.common.EnoteUtil;
import com.enote.dto.CategoryResponseDto;
import com.enote.dto.NoteRequestDto;
import com.enote.dto.NoteResponseDto;
import com.enote.dto.NoteResponseDto.CategorySummary;
import com.enote.dto.NoteResponseDto.FileSummary;
import com.enote.exception.ResourceNotFoundException;
import com.enote.mapper.CategoryMapper;
import com.enote.mapper.FileDetailMapper;
import com.enote.mapper.NoteMapper;
import com.enote.model.CategoryEntity;
import com.enote.model.FileDetailsEntity;
import com.enote.model.NoteEntity;
import com.enote.repository.CategoryRepository;
import com.enote.repository.FileDetailsRepository;
import com.enote.repository.NoteRepository;
import com.enote.service.CategoryService;
import com.enote.service.NoteService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService{
 private final NoteRepository noteRepository;
 private final CategoryRepository categoryRepository;
 private final FileDetailsRepository fileDetailsRepository;
 private final ObjectMapper objectMapper;
 private final NoteMapper noteMapper;
 private final CategoryMapper categoryMapper;
 private final FileDetailMapper fileDetailMapper;
 private final CategoryService categoryService;
 
 @Value("{file.uplpad.path}")
 private String uploadPath;
	@Override
	public NoteResponseDto getNoteById(Integer id) {
		log.info(null);
		NoteEntity existingNoteEntity=noteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Note not found"));
		CategoryEntity existingCategoryEntity = categoryRepository.findById(existingNoteEntity.getCategoryId()).orElseThrow(()->new ResourceNotFoundException("Category not found"));
		FileDetailsEntity existingFileDetailsEntity = fileDetailsRepository.findById(existingNoteEntity.getFileId()).orElseThrow(()->new ResourceNotFoundException("File not found"));
		NoteResponseDto noteResponseDto = noteMapper.entityToResponseDto(existingNoteEntity);
		noteResponseDto.setCategorySummary(getCategory(existingCategoryEntity));
		noteResponseDto.setFileSummary(getFileDetail(existingFileDetailsEntity));
		return noteResponseDto;
	}

	@Override
	public NoteResponseDto createNote(String note,MultipartFile file) throws IOException {
		NoteRequestDto noteRequestDto= objectMapper.readValue(note, NoteRequestDto.class);
		validateNote(noteRequestDto,false);
		FileDetailsEntity savedFileDetailsEntity = setFiledetail(file);
		if (!ObjectUtils.isEmpty(savedFileDetailsEntity)) {
			noteRequestDto.setFileId(savedFileDetailsEntity.getId());
		}
		NoteEntity noteEntity = noteMapper.requestDtoToEntity(noteRequestDto);
		NoteEntity savedNoteEntity = noteRepository.save(noteEntity);
		NoteResponseDto noteResponseDto = noteMapper.entityToResponseDto(savedNoteEntity);
		return noteResponseDto;
	}

	private FileDetailsEntity setFiledetail(MultipartFile file) throws IOException {
		FileDetailsEntity fileDetailsEntity = null ;
		FileDetailsEntity savedFileDetailsEntity=null;
		if (!ObjectUtils.isEmpty(file)  &&  !file.isEmpty()) {
			String originalFilename = file.getOriginalFilename();
			fileDetailsEntity.setOriginalFileName(originalFilename);
			fileDetailsEntity.setDisplayFileName(displayFileName(originalFilename));
			
			String extension = FilenameUtils.getExtension(originalFilename);
			String randomString=UUID.randomUUID().toString();
			String uploadFileName=randomString+"."+extension;
			
			File saveFile=new File(uploadPath);
			if (!saveFile.exists()) {
				saveFile.mkdir();
			}
			String storePath=uploadPath.concat(uploadFileName);
			fileDetailsEntity.setPath(storePath);
			long i = Files.copy(file.getInputStream(),Paths.get(storePath));
			if (i!=0) {
			savedFileDetailsEntity = fileDetailsRepository.save(fileDetailsEntity);
				
			}
		}
		return savedFileDetailsEntity;

	}

	private String displayFileName(String originalFilename) {
		String extension = FilenameUtils.getExtension(originalFilename);
		String fileName = FilenameUtils.removeExtension(originalFilename);
		if (fileName.length()>8) {
		 fileName = fileName.substring(0,7);
		}
		 fileName=fileName+"."+extension;
		return fileName;
	}

	@Transactional
	@Override
	public NoteResponseDto updateNote(Integer id, NoteRequestDto noteRequestDto) {
		NoteEntity existingNoteEntity=noteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Note not found"));
		validateNote(noteRequestDto,true);
		NoteEntity savedNoteEntity = noteRepository.save(existingNoteEntity);
		NoteResponseDto noteResponseDto = noteMapper.entityToResponseDto(savedNoteEntity);
		return noteResponseDto;
	}

	@Transactional
	@Override
	public void deleteNote(Integer id) {
	 NoteEntity existingNoteEntity=noteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Note not found"));
	 Integer deleteBy=2;
	 noteRepository.softDeleteById(id, deleteBy);
	}

//	@Transactional(readOnly = true)
	@Override
	public List<NoteResponseDto> getNotes() {
		List<NoteEntity> noteEntityList  = noteRepository.findAll();
		List<NoteResponseDto> noteResponseDtoList = noteMapper.toResponseDtoList(noteEntityList);
		List<CategoryResponseDto> categoryResponseDtoList = categoryService.getCategories();
		return noteResponseDtoList;
	}
	
	 private void validateNote(NoteRequestDto noteRequestDto,boolean isUpdate){
			 categoryRepository.findById(noteRequestDto.getCategoryId()).orElseThrow(()->new ResourceNotFoundException("Category not found"));
			 fileDetailsRepository.findById(noteRequestDto.getFileId()).orElseThrow(()->new ResourceNotFoundException("File not found"));
			if (EnoteUtil.isNullOrEmpty(noteRequestDto)) {
				throw new IllegalArgumentException("Note cant not be empty");
			}
			if (EnoteUtil.isNullOrEmpty(noteRequestDto.getTitle())) {
				throw new IllegalArgumentException("Title name is required");
			}
			if (EnoteUtil.isNullOrEmpty(noteRequestDto.getDescription())) {
				throw new IllegalArgumentException(" Description is required");
			}
	}
	 
	 
	 private CategorySummary getCategory(CategoryEntity categoryEntity) {
			CategorySummary categorySummary = noteMapper.toCategorySummary(categoryEntity);
			return categorySummary;
	 }
	 
	 
	 private FileSummary getFileDetail(FileDetailsEntity fileDetailsEntity) {
			 FileSummary fileSummary = noteMapper.toFileSummary(fileDetailsEntity);
			return fileSummary;
	 }

	
	

}
