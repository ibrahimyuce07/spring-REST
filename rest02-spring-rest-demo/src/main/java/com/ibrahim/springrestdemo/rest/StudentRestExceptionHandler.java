package com.ibrahim.springrestdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

	// add exception handling code here

	// Add an exception handler using @ExceptionHadler
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

		// create a student response
		StudentErrorResponse errorResponse = new StudentErrorResponse();

		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(exc.getMessage());
		errorResponse.setTimestamp(System.currentTimeMillis());

		// return responseentity

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	// add another exception handler ... to catch any exception
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {

		// create a student response
		StudentErrorResponse errorResponse = new StudentErrorResponse();

		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(exc.getMessage());
		errorResponse.setTimestamp(System.currentTimeMillis());

		// return responseentity

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

	}

}
