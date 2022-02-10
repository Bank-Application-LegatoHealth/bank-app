package com.legato.service;

import com.legato.dto.Response;
import com.legato.dto.TransactionDTO;
import com.legato.exception.BankException;

public interface TransactionService {
	
    public Response saveTranscation(TransactionDTO transDto) throws BankException;
}
