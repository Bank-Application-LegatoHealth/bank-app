package com.legato.entity;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.legato.utility.TransactionType;

@Entity
public class TransactionDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	
	private String referenceNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountNum", referencedColumnName = "accountNum")
	private Account account;
	
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	@CreationTimestamp
	private Timestamp transactionDateTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bankId", referencedColumnName = "bankId")
	private Bank bank;
	
	private String ifsc;
	
	public TransactionDetails() {
		super();
	}

	public TransactionDetails(String referenceNo, Account account, TransactionType transactionType,
			Timestamp transactionDateTime, Bank bank, String ifsc) {
		super();
		this.referenceNo = referenceNo;
		this.account = account;
		this.transactionType = transactionType;
		this.transactionDateTime = transactionDateTime;
		this.bank = bank;
		this.ifsc = ifsc;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(Timestamp transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	@Override
	public String toString() {
		return "TransactionDetails [transactionId=" + transactionId + ", referenceNo=" + referenceNo + ", account="
				+ account + ", transactionType=" + transactionType + ", transactionDateTime=" + transactionDateTime
				+ ", bank=" + bank + ", ifsc=" + ifsc + "]";
	}
	
	
}
