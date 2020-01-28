package com.microservices.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ExceptionNotFound.class)
	public ResponseEntity<Error> handlerExceptionNotFound(ExceptionNotFound ex, WebRequest  request){
		
		Error error= new Error();		 
		 error.setCode(409);
		 error.setDescription(ex.getMessage());
		 error.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
		 
		return  new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	

}
