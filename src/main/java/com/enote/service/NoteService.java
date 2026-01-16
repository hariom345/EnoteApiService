package com.enote.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.enote.dto.NoteRequestDto;
import com.enote.dto.NoteResponseDto;

public interface NoteService {
	public  NoteResponseDto getNoteById(Integer id);
	public NoteResponseDto createNote(String notes,MultipartFile file) throws IOException;
	public NoteResponseDto updateNote(Integer id,NoteRequestDto noteRequestDto);
	public  void deleteNote(Integer id);
	public List<NoteResponseDto> getNotes();
}
