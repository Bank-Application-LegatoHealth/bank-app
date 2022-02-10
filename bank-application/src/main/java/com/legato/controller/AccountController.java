package com.legato.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.legato.dto.AccountDetailResponseDTO;
import com.legato.dto.Response;
import com.legato.dto.TransactionRequestDTO;
import com.legato.dto.TransactionResponseDTO;
import com.legato.exception.BankException;
import com.legato.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService service;
	
	@PostMapping("/showAllTransactions")
	public ResponseEntity<Object> getAllTransactions(@RequestBody TransactionRequestDTO request){
		List<TransactionResponseDTO> transactions = new ArrayList<TransactionResponseDTO>(); 
		try {
			transactions = service.fetchTransactions(request);
			return ResponseEntity.status(HttpStatus.OK).body(transactions);
		} catch (BankException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(404, e.getMessage()));
		}
	}
	
	  @PostMapping("/customers/excel")
	  public ResponseEntity<Object> getFile(@RequestBody TransactionRequestDTO request) {
	    String filename = "Transactions.xlsx";
	    InputStreamResource file;
		try {
			file = new InputStreamResource(service.transToExcel(request));
			return ResponseEntity.ok()
			        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
			        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
			        .body(file);
		} catch (IOException | BankException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest()
					.body(new Response(400,e.getMessage()));
		}
		
	    
	  }

	  @GetMapping(value="/getDetails/{id}")
		public ResponseEntity<Object> getAccountDetail(@PathVariable("id") Long id) {
			try {
			AccountDetailResponseDTO response= service.getAccountDetail(id);	
			return ResponseEntity.status(HttpStatus.OK).body(response);
			}
			catch(BankException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(400, e.getMessage()));
			}
		}
}

