package com.legato.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.legato.dto.Response;
import com.legato.dto.TransactionResponseDTO;
import com.legato.exception.BankException;
import com.legato.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService service;
	
	@GetMapping("/showAllTransactions")
	public ResponseEntity<Object> getAllTransactions(){
		List<TransactionResponseDTO> transactions = new ArrayList<TransactionResponseDTO>(); 
		try {
			transactions = service.fetchAllTransaction();
			return ResponseEntity.status(HttpStatus.OK).body(transactions);
		} catch (BankException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(404, e.getMessage()));
		}
	}
}
