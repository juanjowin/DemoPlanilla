package com.microservices.util;

public class Error {
	
	
	private String status;
	private int code;
	private String description;
	
	
	
	public Error() {
		super();
	}
	
	public String getStatus() {
		return status;
	}
	public int getCode() {
		return code;
	}
	public String getDescription() {
		return description;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}