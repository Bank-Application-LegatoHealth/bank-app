package com.legato.dto;

public class TransactionDTO {
	private String detAccNo;
	private String custName;
	private String ifsc;
	private String amount;
	private String custId;

	public TransactionDTO() {
		super();
	}

	public TransactionDTO(String detAccNo, String custName, String ifsc, String amount, String custId) {
		super();
		this.detAccNo = detAccNo;
		this.custName = custName;
		this.ifsc = ifsc;
		this.amount = amount;
		this.custId = custId;
	}

	public String getDetAccNo() {
		return detAccNo;
	}

	public void setDetAccNo(String detAccNo) {
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	@Override
	public String toString() {
		return "TransactionDTO [detAccNo=" + detAccNo + ", custName=" + custName + ", ifsc=" + ifsc + ", amount="
				+ amount + ", custId=" + custId + "]";
	}

}
