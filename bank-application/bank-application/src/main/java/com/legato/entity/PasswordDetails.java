package com.legato.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PasswordDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long passwordId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "custId", referencedColumnName = "custId")
	private Customer customer;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountNum", referencedColumnName = "accountNum")
	private Account account;
	
	private Long oldLoginPassword;
	
	private Long newLoginPassword;

	@Temporal(TemporalType.TIMESTAMP)
	private Date loginPasswordCreationDateTime;

	private Long oldTransactionPassword;
	
	private Long newTransactionPassword;

	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionPasswordCreationDateTime;

	
	public PasswordDetails() {
		super();
	}
	

	public PasswordDetails(Customer customer, Account account, Long oldLoginPassword, Long newLoginPassword,
			Date loginPasswordCreationDateTime, Long oldTransactionPassword, Long newTransactionPassword,
			Date transactionPasswordCreationDateTime) {
		super();
		this.customer = customer;
		this.account = account;
		this.oldLoginPassword = oldLoginPassword;
		this.newLoginPassword = newLoginPassword;
		this.loginPasswordCreationDateTime = loginPasswordCreationDateTime;
		this.oldTransactionPassword = oldTransactionPassword;
		this.newTransactionPassword = newTransactionPassword;
		this.transactionPasswordCreationDateTime = transactionPasswordCreationDateTime;
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Long getOldLoginPassword() {
		return oldLoginPassword;
	}

	public void setOldLoginPassword(Long oldLoginPassword) {
		this.oldLoginPassword = oldLoginPassword;
	}

	public Long getNewLoginPassword() {
		return newLoginPassword;
	}

	public void setNewLoginPassword(Long newLoginPassword) {
		this.newLoginPassword = newLoginPassword;
	}

	public Date getLoginPasswordCreationDateTime() {
		return loginPasswordCreationDateTime;
	}

	public void setLoginPasswordCreationDateTime(Date loginPasswordCreationDateTime) {
		this.loginPasswordCreationDateTime = loginPasswordCreationDateTime;
	}

	public Long getOldTransactionPassword() {
		return oldTransactionPassword;
	}

	public void setOldTransactionPassword(Long oldTransactionPassword) {
		this.oldTransactionPassword = oldTransactionPassword;
	}

	public Long getNewTransactionPassword() {
		return newTransactionPassword;
	}

	public void setNewTransactionPassword(Long newTransactionPassword) {
		this.newTransactionPassword = newTransactionPassword;
	}

	public Date getTransactionPasswordCreationDateTime() {
		return transactionPasswordCreationDateTime;
	}

	public void setTransactionPasswordCreationDateTime(Date transactionPasswordCreationDateTime) {
		this.transactionPasswordCreationDateTime = transactionPasswordCreationDateTime;
	}

	public Long getPasswordId() {
		return passwordId;
	}

	@Override
	public String toString() {
		return "PasswordDetails [passwordId=" + passwordId + ", customer=" + customer + ", account=" + account
				+ ", oldLoginPassword=" + oldLoginPassword + ", newLoginPassword=" + newLoginPassword
				+ ", loginPasswordCreationDateTime=" + loginPasswordCreationDateTime + ", oldTransactionPassword="
				+ oldTransactionPassword + ", newTransactionPassword=" + newTransactionPassword
				+ ", transactionPasswordCreationDateTime=" + transactionPasswordCreationDateTime + "]";
	}
	
	

}
