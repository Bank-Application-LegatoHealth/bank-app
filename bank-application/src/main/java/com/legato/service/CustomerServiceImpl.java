package com.legato.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legato.dto.LoginRequestDTO;
import com.legato.dto.Response;
import com.legato.entity.Customer;
import com.legato.exception.BankException;
import com.legato.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Response performLogin(LoginRequestDTO loginRequestDTO) throws BankException {
		
		if(Objects.isNull(loginRequestDTO.getCustId()) || Objects.isNull(loginRequestDTO.getPassword())) {
			throw new BankException("Customer id Or password should not be empty");
		}
		Optional<Customer> customerOptional = customerRepository.findById(loginRequestDTO.getCustId());
		
		
		if(!customerOptional.isPresent()){
			throw new BankException("Customer is not found!");
		}
		
		Customer cutomer = customerOptional.get();
		if (!cutomer.getCustPassword().equals(loginRequestDTO.getPassword())) {
			throw new BankException("Entered password is wrong!");
		}
		return new  Response(200,"Loggin successfull!");
	}

}
