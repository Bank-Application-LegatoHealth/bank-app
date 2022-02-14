package com.legato.dto;

public class AccountDetailResponseDTO {

	private String custName;
	
	private Long accNumber;
	
	private String type;
	
	private Double avlbalance;

	private String bankName;
	
	private String bankIfsc;
	
	private Double transferLimit; 

	private Double  minBalance;
	
	private String bankAdress;
	
	
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


	public AccountDetailResponseDTO() {}
	

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankIfsc() {
		return bankIfsc;
	}

	public void setBankIfsc(String bankIfsc) {
		this.bankIfsc = bankIfsc;
	}

	public Double getTransferLimit() {
		return transferLimit;
	}

	public void setTransferLimit(Double transferLimit) {
		this.transferLimit = transferLimit;
	}

	public Double getMinBalance() {
		return minBalance;
	}

	public void setMinBalance(Double minBalance) {
		this.minBalance = minBalance;
	}

	public String getBankAdress() {
		return bankAdress;
	}

	public void setBankAdress(String bankAdress) {
		this.bankAdress = bankAdress;
	}

	public AccountDetailResponseDTO(String custName, Long accNumber, String type, Double avlbalance, String bankName,
			String bankIfsc, Double transferLimit, Double minBalance, String bankAdress) {
		super();
		this.custName = custName;
		this.accNumber = accNumber;
		this.type = type;
		this.avlbalance = avlbalance;
		this.bankName = bankName;
		this.bankIfsc = bankIfsc;
		this.transferLimit = transferLimit;
		this.minBalance = minBalance;
		this.bankAdress = bankAdress;
	}

	@Override
	public String toString() {
		return "AccountDetailResponseDTO [custName=" + custName + ", accNumber=" + accNumber + ", type=" + type
				+ ", avlbalance=" + avlbalance + ", bankName=" + bankName + ", bankIfsc=" + bankIfsc
				+ ", transferLimit=" + transferLimit + ", minBalance=" + minBalance + ", bankAdress=" + bankAdress
				+ "]";
	}
	
	
}
