package com.legato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.legato.dto.ChangePasswordRequestDTO;
import com.legato.dto.Response;
import com.legato.exception.BankException;
import com.legato.service.PasswordService;

@RestController
@CrossOrigin
public class PasswordController {

	@Autowired
	private PasswordService passwordService;

	@PutMapping(path = "/changePassword", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> changePassword(@RequestBody ChangePasswordRequestDTO changePasswordRequestDTO) {

		try {
			Response response = passwordService.changePassword(changePasswordRequestDTO);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (BankException e) {
			return ResponseEntity.status(HttpStatus.OK).body(new Response(400, e.getMessage()));
		}

	}

}
