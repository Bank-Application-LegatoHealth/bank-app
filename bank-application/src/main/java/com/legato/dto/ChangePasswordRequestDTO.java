package com.legato.dto;

import com.legato.utility.PasswordType;

public class ChangePasswordRequestDTO {

	private Long custId;

	private String oldPassword;

	private String newPassword;

	private String confirmPassword;

	private PasswordType passwordType;

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public PasswordType getPasswordType() {
		return passwordType;
	}

	public void setPasswordType(PasswordType passwordType) {
		this.passwordType = passwordType;
	}

	public ChangePasswordRequestDTO(Long custId, Long accountNum, String oldPassword, String newPassword,
			String confirmPassword, PasswordType passwordType) {
		super();
		this.custId = custId;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
		this.passwordType = passwordType;
	}

	@Override
	public String toString() {
		return "ChangePasswordRequestDTO [custId=" + custId + ", accountNum=" + ", oldPassword="
				+ oldPassword + ", newPassword=" + newPassword + ", confirmPassword=" + confirmPassword
				+ ", passwordType=" + passwordType + "]";
	}

	public ChangePasswordRequestDTO() {
		super();
	
	}

}
