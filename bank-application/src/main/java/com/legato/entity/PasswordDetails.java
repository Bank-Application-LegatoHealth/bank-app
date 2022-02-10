package com.legato.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	
	private String oldLoginPassword;
	
	private String newLoginPassword;

	@UpdateTimestamp
	private Timestamp loginPasswordCreationDateTime;

	private String oldTransactionPassword;
	
	private String newTransactionPassword;

	@UpdateTimestamp
	private Timestamp transactionPasswordCreationDateTime;

	
	public PasswordDetails() {
		super();
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

	public String getOldLoginPassword() {
		return oldLoginPassword;
	}

	public void setOldLoginPassword(String oldLoginPassword) {
		this.oldLoginPassword = oldLoginPassword;
	}

	public String getNewLoginPassword() {
		return newLoginPassword;
	}

	public void setNewLoginPassword(String newLoginPassword) {
		this.newLoginPassword = newLoginPassword;
	}

	public Timestamp getLoginPasswordCreationDateTime() {
		return loginPasswordCreationDateTime;
	}

	public void setLoginPasswordCreationDateTime(Timestamp loginPasswordCreationDateTime) {
		this.loginPasswordCreationDateTime = loginPasswordCreationDateTime;
	}

	public String getOldTransactionPassword() {
		return oldTransactionPassword;
	}

	public void setOldTransactionPassword(String oldTransactionPassword) {
		this.oldTransactionPassword = oldTransactionPassword;
	}

	public String getNewTransactionPassword() {
		return newTransactionPassword;
	}

	public void setNewTransactionPassword(String newTransactionPassword) {
		this.newTransactionPassword = newTransactionPassword;
	}

	public Timestamp getTransactionPasswordCreationDateTime() {
		return transactionPasswordCreationDateTime;
	}

	public void setTransactionPasswordCreationDateTime(Timestamp transactionPasswordCreationDateTime) {
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

	public PasswordDetails(Customer customer, Account account, String oldLoginPassword, String newLoginPassword,
			Timestamp loginPasswordCreationDateTime, String oldTransactionPassword, String newTransactionPassword,
			Timestamp transactionPasswordCreationDateTime) {
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
	
	

}
