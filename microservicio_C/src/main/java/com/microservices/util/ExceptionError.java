package com.microservices.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExceptionError extends RuntimeException {

	public ExceptionError(String message) {
		super(message);
		
	}

}
