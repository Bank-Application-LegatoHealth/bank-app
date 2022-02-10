package com.legato.dto;

public class LoginRequestDTO {

	private Long custId;
	private String password;
	
	public LoginRequestDTO(Long custId, String password) {
		super();
		this.custId = custId;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "LoginRequestDTO [custId=" + custId + ", password=" + password + "]";
	}
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
