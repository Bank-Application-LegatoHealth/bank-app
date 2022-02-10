package com.legato.entity;


import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.UpdateTimestamp;

import com.legato.utility.AccountType;

@Entity
public class Account {
	
	@Id
	private Long accountNum;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "custId", referencedColumnName = "custId")
	private Customer customer;
	
	@Enumerated(EnumType.STRING)
	private AccountType accType;
	
	private String ifsc;
	
	private String transactionPassword;
	
	@UpdateTimestamp
	private Timestamp createdDateTime;
	
	private Double transferLimit;
	
	private Double minimumBalance;
	
	private Double availableBalance;

	public Account() {
		super();
		
	}
	public Double getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(Double availableBalance) {
		this.availableBalance = availableBalance;
	}
	public Account(Customer customer, AccountType accType, String ifsc, String transactionPassword,
			Timestamp createdDateTime, Double transferLimit, Double minimumBalance,Double availableBalance) {
		super();
		this.customer = customer;
		this.accType = accType;
		this.ifsc = ifsc;
		this.transactionPassword = transactionPassword;
		this.createdDateTime = createdDateTime;
		this.transferLimit = transferLimit;
		this.minimumBalance = minimumBalance;
		this.availableBalance = availableBalance;
	}
	

	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public AccountType getAccType() {
		return accType;
	}

	public void setAccType(AccountType accType) {
		this.accType = accType;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getTransactionPassword() {
		return transactionPassword;
	}

	public void setTransactionPassword(String transactionPassword) {
		this.transactionPassword = transactionPassword;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Double getTransferLimit() {
		return transferLimit;
	}

	public void setTransferLimit(Double transferLimit) {
		this.transferLimit = transferLimit;
	}

	public Double getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(Double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	public Long getAccountNum() {
		return accountNum;
	}

	@Override
	public String toString() {
		return "Account [accountNum=" + accountNum + ", customer=" + customer + ", accType=" + accType + ", ifsc="
				+ ifsc + ", transactionPassword=" + transactionPassword + ", createdDateTime=" + createdDateTime
				+ ", transferLimit=" + transferLimit + ", minimumBalance=" + minimumBalance + ", availableBalance="
				+ availableBalance + "]";
	}
	
	

}
