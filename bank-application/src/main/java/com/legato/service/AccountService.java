package com.legato.service;

import java.util.List;

import com.legato.dto.TransactionResponseDTO;
import com.legato.exception.BankException;

public interface AccountService {
	
	public List<TransactionResponseDTO> fetchAllTransaction() throws BankException;

}
