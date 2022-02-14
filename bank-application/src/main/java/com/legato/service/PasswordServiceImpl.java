package com.legato.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legato.dto.ChangePasswordRequestDTO;
import com.legato.dto.Response;
import com.legato.entity.Account;
import com.legato.entity.Customer;
import com.legato.entity.PasswordDetails;
import com.legato.exception.BankException;
import com.legato.repository.AccountRepository;
import com.legato.repository.CustomerRepository;
import com.legato.repository.PasswordDetailsRepository;
import com.legato.utility.PasswordType;

@Service
public class PasswordServiceImpl implements PasswordService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PasswordDetailsRepository passwordDetailsRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public Response changePassword(ChangePasswordRequestDTO changePasswordRequestDTO) throws BankException {

		Customer customer = validateRequestLoginPassword(changePasswordRequestDTO);
		Account account = validateRequestTransactionPassword(changePasswordRequestDTO);
		PasswordDetails passwordDetails = passwordDetailsRepository.getPasswordByCustId(customer.getCustId());

		if (Objects.nonNull(passwordDetails)) {
			if (changePasswordRequestDTO.getPasswordType().equals(PasswordType.TRANSACTION)) {
				if(!passwordDetails.getNewTransactionPassword().equals(changePasswordRequestDTO.getOldPassword())) {
					throw new BankException("you have entered wrong old transaction password");
				}
				passwordDetails.setOldTransactionPassword(passwordDetails.getNewTransactionPassword());
				passwordDetails.setNewTransactionPassword(changePasswordRequestDTO.getNewPassword());
			}
			if (changePasswordRequestDTO.getPasswordType().equals(PasswordType.LOGIN)) {
				if (!passwordDetails.getNewLoginPassword().equals(changePasswordRequestDTO.getOldPassword())) {
					throw new BankException("you have entered wrong old login password");
				}
				passwordDetails.setOldLoginPassword(passwordDetails.getNewLoginPassword());
				passwordDetails.setNewLoginPassword(changePasswordRequestDTO.getNewPassword());
				customer.setCustPassword(changePasswordRequestDTO.getNewPassword());
				customerRepository.save(customer);
			}
		}
		passwordDetailsRepository.save(passwordDetails);
		
		return new Response(200, "password changed succesfully for "
				+ changePasswordRequestDTO.getPasswordType().toString().toLowerCase());
	}

	private Account validateRequestTransactionPassword(ChangePasswordRequestDTO changePasswordRequestDTO) throws BankException {
		if (!Objects.nonNull(changePasswordRequestDTO)) {
			throw new BankException("Request body should not be empty!");
		}

		if (changePasswordRequestDTO.getOldPassword().equals(changePasswordRequestDTO.getNewPassword())) {
			throw new BankException("Old password and new password should not be same");
		}

		if (!changePasswordRequestDTO.getConfirmPassword().equals(changePasswordRequestDTO.getNewPassword())) {
			throw new BankException(" new password and confirm password should be same");
		}
		Account account = accountRepository.getAccountByCustId(changePasswordRequestDTO.getCustId());
		if (Objects.nonNull(account)){
			throw new BankException("customer id is invalid");
		}
		
		if (Objects.isNull(account)) {
			throw new BankException("customer not found");
		}
		return account;
	}

	private Customer validateRequestLoginPassword(ChangePasswordRequestDTO changePasswordRequestDTO) throws BankException {

		if (!Objects.nonNull(changePasswordRequestDTO)) {
			throw new BankException("Request body should not be empty!");
		}

		if (changePasswordRequestDTO.getOldPassword().equals(changePasswordRequestDTO.getNewPassword())) {
			throw new BankException("Old password and new password should not be same");
		}

		if (!changePasswordRequestDTO.getConfirmPassword().equals(changePasswordRequestDTO.getNewPassword())) {
			throw new BankException(" new password and confirm password should be same");
		}
		Optional<Customer> optionalCustomer = customerRepository.findById(changePasswordRequestDTO.getCustId());
		if (!optionalCustomer.isPresent()) {
			throw new BankException("customer id is invalid");
		}
		Customer customer = optionalCustomer.get();
		if (Objects.isNull(customer)) {
			throw new BankException("customer not found");
		}
		return customer;
	}

}
