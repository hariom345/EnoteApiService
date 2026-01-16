package com.enote.exception;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.enote.common.ResponseUtil;

@RestControllerAdvice
public class GlobalException {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?>  handleResourceNotFoundException(ResourceNotFoundException e){
		return ResponseUtil.notFound(e.getMessage());		
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?>  handleMessageNotReadableException(HttpMessageNotReadableException e){
	return ResponseUtil.badRequest(e.getMessage());		
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<?>  handleMissingServletRequestParameterException(MissingServletRequestParameterException e){
	return ResponseUtil.badRequest(e.getMessage());		
	}
	
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public ResponseEntity<?>  handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e){
	return ResponseUtil.internalServerError(e.getMessage());		
	}

}
