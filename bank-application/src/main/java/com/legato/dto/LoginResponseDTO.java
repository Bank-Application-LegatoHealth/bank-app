package com.legato.dto;

public class LoginResponseDTO {

	private String streetName;

	private String State;

	private String City;

	private Integer pin;

	private Long custId;
	
	private String custName;
	
	private Integer age;

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

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
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

	@Override
	public String toString() {
		return "LoginResponseDTO [streetName=" + streetName + ", State=" + State + ", City=" + City + ", pin=" + pin
				+ ", custId=" + custId + ", custName=" + custName + ", age=" + age + "]";
	}

	public LoginResponseDTO(String streetName, String state, String city, Integer pin, Long custId, String custName,
			Integer age) {
		super();
		this.streetName = streetName;
		State = state;
		City = city;
		this.pin = pin;
		this.custId = custId;
		this.custName = custName;
		this.age = age;
	}

	public LoginResponseDTO() {
		super();
	}
	
}
