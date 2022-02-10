package com.legato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.legato.dto.LoginRequestDTO;
import com.legato.dto.Response;
import com.legato.exception.BankException;
import com.legato.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> performLogin(@RequestBody LoginRequestDTO loginRequestDTO) {
		try {
			Response response = customerService.performLogin(loginRequestDTO);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (BankException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(400, e.getMessage()));
		}
	}
}
