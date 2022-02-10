package com.legato.dto;

public class AccountDetailResponseDTO {

	private String custName;
	
	private Long accNumber;
	
	private String type;
	
	private Double avlbalance;

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Long getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(Long accNumber) {
		this.accNumber = accNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAvlbalance() {
		return avlbalance;
	}

	public void setAvlbalance(Double avlbalance) {
		this.avlbalance = avlbalance;
	}

	@Override
	public String toString() {
		return "AccountDetailResponseDTO [custName=" + custName + ", accNumber=" + accNumber + ", type=" + type
				+ ", avlbalance=" + avlbalance + "]";
	}

	
	public AccountDetailResponseDTO() {}
	
	public AccountDetailResponseDTO(String custName, Long accNumber, String type, Double avlbalance) {
		super();
		this.custName = custName;
		this.accNumber = accNumber;
		this.type = type;
		this.avlbalance = avlbalance;
	}
	
	
}
