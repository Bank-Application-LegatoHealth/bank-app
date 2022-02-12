package com.legato.dto;

public class TransactionDTO {
	private Long detAccNo;
	private String custName;
	private String ifsc;
	private Double amount;
	private Long custId;
    private String transPass;
	public TransactionDTO() {
		super();
	}

	public TransactionDTO(Long detAccNo, String custName, String ifsc, Double amount, Long custId) {
		super();
		this.detAccNo = detAccNo;
		this.custName = custName;
		this.ifsc = ifsc;
		this.amount = amount;
		this.custId = custId;
	}

	public Long getDetAccNo() {
		return detAccNo;
	}

	public void setDetAccNo(Long detAccNo) {
		this.detAccNo = detAccNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}
	
	

	public String getTransPass() {
		return transPass;
	}

	public void setTransPass(String transPass) {
		this.transPass = transPass;
	}

	@Override
	public String toString() {
		return "TransactionDTO [detAccNo=" + detAccNo + ", custName=" + custName + ", ifsc=" + ifsc + ", amount="
				+ amount + ", custId=" + custId + "]";
	}

}
