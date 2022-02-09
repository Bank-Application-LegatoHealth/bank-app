package com.legato.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bankId;
	private String name;
	private String ifsc;
	private String branch;
	private Integer noOfCustomers;
	
	public Bank(){
		
	}
	public Bank(String name, String ifsc, String branch, Integer noOfCustomers) {
		super();
		this.name = name;
		this.ifsc = ifsc;
		this.branch = branch;
		this.noOfCustomers = noOfCustomers;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Integer getNoOfCustomers() {
		return noOfCustomers;
	}
	public void setNoOfCustomers(Integer noOfCustomers) {
		this.noOfCustomers = noOfCustomers;
	}
	public Long getBankId() {
		return bankId;
	}
	
	@Override
	public String toString() {
		return "Bank [bankId=" + bankId + ", name=" + name + ", ifsc=" + ifsc + ", branch=" + branch
				+ ", noOfCustomers=" + noOfCustomers + "]";
	}

}
