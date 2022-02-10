package com.legato.service;

import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legato.dto.Response;
import com.legato.dto.TransactionDTO;
import com.legato.entity.Account;
import com.legato.entity.Bank;
import com.legato.entity.Customer;
import com.legato.entity.TransactionDetails;
import com.legato.exception.BankException;
import com.legato.repository.AccountRepository;
import com.legato.repository.CustomerRepository;
import com.legato.repository.TransactionDetailsRepository;
import com.legato.utility.TransactionType;

@Service
public class TransactionServiceImpl implements TransactionService {
	

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TransactionDetailsRepository transactionDetailsRepository;

	@Override
	@Transactional
	public Response saveTranscation(TransactionDTO transDto) throws BankException {
		
		if (Objects.isNull(transDto.getDetAccNo()) || Objects.isNull(transDto.getAmount())
				|| Objects.isNull(transDto.getCustId()) || Objects.isNull(transDto.getCustName())
				|| Objects.isNull(transDto.getIfsc())) {
			throw new BankException(
					"Dest Account or Customer ID Or Amount or CustomerName or IFSC should not be empty");
		}
		//DEBIT DETAILS
		Account debitAcc = null;
		Bank debitBank=null;
		
		//CREDIT DETAILS
		Account crAcc = null;
		Customer creditCust = null;
		Bank creditBank = null;
		
		// Converting DTO
		Long destAcc = Long.parseLong(transDto.getDetAccNo());
		Long debitcustId = Long.parseLong(transDto.getCustId());
		Double amount = Double.parseDouble(transDto.getAmount());

		String referenceNo = getRandomString(15).toUpperCase();
		System.out.println(referenceNo.toUpperCase());
		
		
		// DEBIT ACCOUNT Details
		Optional<Customer> debitCust = customerRepository.findById(debitcustId);
		if(!debitCust.isPresent()) {
			throw new BankException("!! Source Customer Not Found !!");
		}
	    debitAcc = accountRepository.getAccountByCustId(debitcustId);
		debitBank = debitCust.get().getBank();
		
		// CREDIT ACCOUNT Details
		Optional<Account> creditAcc = accountRepository.findById(Long.parseLong(transDto.getDetAccNo()));
		if(!creditAcc.isPresent()) {
			throw new BankException(" !! Destination Customer Not Found !! ");
		}
		crAcc = creditAcc.get();
		creditCust = creditAcc.get().getCustomer();
		creditBank = creditCust.getBank();

//		// Validation Amount for Debit Account
//		if (debitAcc.getAvailableBalance() >= amount) {
//			debitAcc.setAvailableBalance(debitAcc.getAvailableBalance() - amount);
//			accountRepository.save(debitAcc);
//			TransactionDetails debitDetails = new TransactionDetails();
//			debitDetails.setAccount(debitAcc);
//			debitDetails.setIfsc(transDto.getIfsc());
//			debitDetails.setReferenceNo(referenceNo);
//			debitDetails.setTransactionType(TransactionType.DEBIT);
//			debitDetails.setBank(debitBank);
//			transactionDetailsRepository.save(debitDetails);
//		} else {
//			throw new BankException("!! Amount Validation Failed for Customer !!");
//		}

		// Validating Credit Account Details And Customer Name
		if (crAcc.getAccountNum().equals(destAcc) && crAcc.getIfsc().equals(transDto.getIfsc())
				&& creditCust.getCustName().equals(transDto.getCustName()) && debitAcc.getAvailableBalance() >= amount) {
			
			//DEBIT
			debitAcc.setAvailableBalance(debitAcc.getAvailableBalance() - amount);
			accountRepository.save(debitAcc);
			TransactionDetails debitDetails = new TransactionDetails();
			debitDetails.setAccount(debitAcc);
			debitDetails.setIfsc(transDto.getIfsc());
			debitDetails.setReferenceNo(referenceNo);
			debitDetails.setTransactionType(TransactionType.DEBIT);
			debitDetails.setBank(debitBank);
			debitDetails.setAmount(amount);
			transactionDetailsRepository.save(debitDetails);
			
			//CREDIT
			crAcc.setAvailableBalance(crAcc.getAvailableBalance() + amount);
			accountRepository.save(crAcc);
			TransactionDetails creditDetails = new TransactionDetails();
			creditDetails.setAccount(crAcc);
			creditDetails.setBank(creditBank);
			creditDetails.setIfsc(transDto.getIfsc());
			creditDetails.setReferenceNo(referenceNo);
			creditDetails.setTransactionType(TransactionType.CREDIT);
			creditDetails.setAmount(amount);
			transactionDetailsRepository.save(creditDetails);
			return new Response(200, "!! Transfer Completed Successfully !!");
		} else {
			throw new BankException("!! Source Customer Amount Vaidation Failed OR Destination Customer Validation Failed  !!");
		}	
	}

	public String getRandomString(int n) {
		// length is bounded by 256 Character
		byte[] array = new byte[256];
		new Random().nextBytes(array);
		String randomString = new String(array, Charset.forName("UTF-8"));
		// Create a StringBuffer to store the result
		StringBuffer r = new StringBuffer();
		// remove all spacial char
		String AlphaNumericString = randomString.replaceAll("[^A-Za-z0-9]", "");
		// Append first 20 alphanumeric characters
		// from the generated random String into the result
		for (int k = 0; k < AlphaNumericString.length(); k++) {
			if (Character.isLetter(AlphaNumericString.charAt(k)) && (n > 0)
					|| Character.isDigit(AlphaNumericString.charAt(k)) && (n > 0)) {
				r.append(AlphaNumericString.charAt(k));
				n--;
			}
		}
		return r.toString();
	}

}
