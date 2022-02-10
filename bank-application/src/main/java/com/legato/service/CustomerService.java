package com.legato.service;

import com.legato.dto.LoginRequestDTO;
import com.legato.dto.LoginResponseDTO;
import com.legato.dto.Response;
import com.legato.exception.BankException;

public interface CustomerService {

	public Response performLogin(LoginRequestDTO loginRequestDTO) throws BankException;
}
