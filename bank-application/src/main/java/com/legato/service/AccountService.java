package com.legato.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.legato.dto.TransactionRequestDTO;
import com.legato.dto.TransactionResponseDTO;
import com.legato.exception.BankException;

public interface AccountService {
	
	public List<TransactionResponseDTO> fetchTransactions(TransactionRequestDTO dto) throws BankException;
	
	public ByteArrayInputStream transToExcel(TransactionRequestDTO request) throws IOException,BankException;

}
