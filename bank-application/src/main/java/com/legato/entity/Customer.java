package com.legato.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long custId;
	private String custName;
	private Integer age;
	
	private String custPassword;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bankId", referencedColumnName = "bankId")
	private Bank bank;
	
	public Customer() {
		
	}

	public Customer(String custName, Integer age, String custPassword, Bank bank) {
		super();
		this.custName = custName;
		this.age = age;
		this.custPassword = custPassword;
		this.bank = bank;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCustPassword() {
		return custPassword;
	}

	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Long getCustId() {
		return custId;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", age=" + age + ", custPassword="
				+ custPassword + ", bank=" + bank + "]";
	}
	

	
	

}
