package com.legato.service;

import com.legato.dto.ChangePasswordRequestDTO;
import com.legato.dto.Response;
import com.legato.exception.BankException;

public interface PasswordService {

	public Response changePassword(ChangePasswordRequestDTO changePasswordRequestDTO) throws BankException;
}
