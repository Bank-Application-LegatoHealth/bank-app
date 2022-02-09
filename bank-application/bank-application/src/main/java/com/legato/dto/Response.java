package com.legato.dto;

import org.springframework.http.HttpStatus;

public class Response {
	
	private HttpStatus statusCode;
	String message;
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
