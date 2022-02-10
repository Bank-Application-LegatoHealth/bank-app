package com.legato.dto;

public class Response {
	
	private Integer statusCode;
	String message;
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Response(Integer statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}
	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
