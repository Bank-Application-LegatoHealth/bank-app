package com.legato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.legato.dto.Response;
import com.legato.dto.TransactionDTO;
import com.legato.exception.BankException;
import com.legato.service.TransactionService;

@RestController
public class TransactionController {
	
	@Autowired
	private TransactionService service;
	
	@PostMapping("/transfer")
	public ResponseEntity<Object> transferAmount(@RequestBody TransactionDTO transDto){
		Response response = null;
		try {
			response = service.saveTranscation(transDto);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (BankException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(400,e.getMessage()));
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(400,e.getMessage()));
		}
	}

}
