package com.microservices.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ExceptionError.class)
	public ResponseEntity<Error> handlerExceptionError(ExceptionError ex, WebRequest request){
		
		Error error = new Error();
		
		error.setStatus(HttpStatus.CONFLICT.getReasonPhrase());
		error.setCode(409);
		error.setDescription(ex.getMessage());
		
		
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		
	}

}
