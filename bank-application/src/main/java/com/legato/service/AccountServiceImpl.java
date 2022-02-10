package com.legato.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legato.dto.TransactionResponseDTO;
import com.legato.entity.TransactionDetails;
import com.legato.exception.BankException;
import com.legato.repository.TransactionDetailsRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private TransactionDetailsRepository repository;
	
	public List<TransactionResponseDTO> fetchAllTransaction() throws BankException {
		List<TransactionDetails> allTransactions = repository.findAll();
		
		if(allTransactions.isEmpty() || Objects.isNull(allTransactions)) 
			throw new BankException("No Transactions found.");
		
		List<TransactionResponseDTO> transList = new ArrayList<TransactionResponseDTO>();
		
		for (TransactionDetails transactions : allTransactions) {
			
			transList.add(new TransactionResponseDTO(transactions.getTransactionId(),
					transactions.getReferenceNo(),
					transactions.getAccount().getAccountNum(),
					transactions.getTransactionType(),
					transactions.getAmount(),
					transactions.getTransactionDateTime(),
					transactions.getIfsc()));
		}
		
		return transList;
	}
}
