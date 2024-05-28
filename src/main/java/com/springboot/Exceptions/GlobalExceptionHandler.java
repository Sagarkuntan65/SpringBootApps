package com.springboot.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		int num = ex.fieldValue;
		ApiResponse apiResponse = new ApiResponse(message, false, num);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {
		Map<String, String> hmap = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fielderror = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			hmap.put(fielderror, message);
		});
		return new ResponseEntity<Map<String, String>>(hmap, HttpStatus.BAD_REQUEST);

	}
}
