package com.enote.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enote.common.ApiConstants;
import com.enote.common.GenericResponse;
import com.enote.common.ResponseUtil;
import com.enote.dto.NoteRequestDto;
import com.enote.dto.NoteResponseDto;
import com.enote.service.NoteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.Note_ROOT_API)
public class NoteController {
	private final NoteService noteService;
	
	@GetMapping(ApiConstants.GET_Note_BY_ID)
	public ResponseEntity<GenericResponse<NoteResponseDto>> getNoteById(@PathVariable Integer id) {
	return ResponseUtil.success(noteService.getNoteById(id), "Note found Successfully");
	 
	}
	
	@PostMapping(ApiConstants.CREATE_NOTE)
	public ResponseEntity<GenericResponse<NoteResponseDto>> createNote(@RequestParam String notes, @RequestParam(required =  false) MultipartFile  file) throws IOException{
    return ResponseUtil.created(noteService.createNote(notes,file), "Note added Successfully");

	}
	
	@PutMapping(ApiConstants.UPDATE_NOTE)
	public ResponseEntity<GenericResponse<NoteResponseDto>> updateNote( @RequestBody @Valid NoteRequestDto noteRequestDto,@PathVariable("id")Integer id ){
	return ResponseUtil.success(noteService.updateNote(id,noteRequestDto), "Category updated Successfully");

	}
	
	@DeleteMapping(ApiConstants.DELETE_NOTE)
	public ResponseEntity<GenericResponse<Void>> deleteNote(@PathVariable("id")Integer id ){
	noteService.deleteNote(id);
	return ResponseUtil.success("Note deleted Successfully");

	}
	
	@GetMapping
	public ResponseEntity<GenericResponse<List<NoteResponseDto>>> getNotes(){
	return ResponseUtil.success(noteService.getNotes(), "Notes found Successfully");

	}


	
	

}
