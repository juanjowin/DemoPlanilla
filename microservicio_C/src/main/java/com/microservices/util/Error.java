package com.microservices.util;

public class Error {

	private String httpStatus;
	private int code;
	private String description;
	
	


	public Error() {
		super();
	}


	public Error(String httpStatus, int code, String description) {
		super();
		this.httpStatus = httpStatus;
		this.code = code;
		this.description = description;
	}


	public String getHttpStatus() {
		return httpStatus;
	}


	public int getCode() {
		return code;
	}


	public String getDescription() {
		return description;
	}


	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	

	
	
}
