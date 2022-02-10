package com.legato.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	
	private String streetName;
	
	private String State;
	
	private String City;
	
	private Integer pin;
	
	@OneToOne
	@JoinColumn(name = "custId", referencedColumnName = "custId")
	private Customer customer;
	
	public Address() {
		super();
	}

	public Address(Long addressId, String streetName, String state, String city, Integer pin, Customer customer) {
		super();
		this.addressId = addressId;
		this.streetName = streetName;
		State = state;
		City = city;
		this.pin = pin;
		this.customer = customer;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getAddressId() {
		return addressId;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", streetName=" + streetName + ", State=" + State + ", City=" + City
				+ ", pin=" + pin + ", customer=" + customer + "]";
	}
	
}
