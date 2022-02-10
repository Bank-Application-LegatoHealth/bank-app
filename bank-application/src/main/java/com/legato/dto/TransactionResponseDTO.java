package com.legato.dto;

import java.sql.Timestamp;
import java.util.Date;

import com.legato.utility.TransactionType;
public class TransactionResponseDTO {

	private Long transactionId;
	
	private String referenceNo;
	
	private Long accountNum;
	
	private TransactionType transactionType;
	
	private Double amount;
	
	private Date transactionDateTime;
	
	private String ifsc;

	public TransactionResponseDTO() {
		super();
	}

	public TransactionResponseDTO(Long transactionId, String referenceNo, Long accountNum,
			TransactionType transactionType, Double amount, Date date, String ifsc) {
		
		this.transactionId = transactionId;
		this.referenceNo = referenceNo;
		this.accountNum = accountNum;
		this.transactionType = transactionType;
		this.amount = amount;
		this.transactionDateTime = date;
		this.ifsc = ifsc;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public Long getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(Long accountNum) {
		this.accountNum = accountNum;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(Timestamp transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	@Override
	public String toString() {
		return "TransactionResponseDTO [transactionId=" + transactionId + ", referenceNo=" + referenceNo
				+ ", accountNum=" + accountNum + ", transactionType=" + transactionType + ", amount=" + amount
				+ ", transactionDateTime=" + transactionDateTime + ", ifsc=" + ifsc + "]";
	}

}
