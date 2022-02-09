package com.legato.dto;

public class LoginRequestDTO {

	private String custId;
	private String password;
	
	public LoginRequestDTO(String custId, String password) {
		super();
		this.custId = custId;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "LoginRequestDTO [custId=" + custId + ", password=" + password + "]";
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
