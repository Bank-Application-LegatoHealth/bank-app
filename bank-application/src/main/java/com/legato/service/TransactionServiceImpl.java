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
import com.legato.entity.PasswordDetails;
import com.legato.entity.TransactionDetails;
import com.legato.exception.BankException;
import com.legato.repository.AccountRepository;
import com.legato.repository.CustomerRepository;
import com.legato.repository.PasswordDetailsRepository;
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

	@Autowired
	private PasswordDetailsRepository passwordDetailsRepository;

	@Override
	@Transactional
	public Response saveTranscation(TransactionDTO transDto) throws BankException {
		// DEBIT DETAILS
		Account debitAcc = null;
		Bank debitBank = null;
		PasswordDetails debitpasswordDetails = null;

		// CREDIT DETAILS
		Account crAcc = null;
		Customer creditCust = null;
		Bank creditBank = null;

		if (Objects.isNull(transDto.getDetAccNo()) || Objects.isNull(transDto.getAmount())
				|| Objects.isNull(transDto.getCustId()) || Objects.isNull(transDto.getCustName())
				|| Objects.isNull(transDto.getIfsc())) {
			throw new BankException(
					"Dest Account or Customer ID Or Amount or CustomerName or IFSC should not be empty");
		}

		Long destAcc = transDto.getDetAccNo();
		Long debitcustId = transDto.getCustId();
		Double amount = transDto.getAmount();

		// Amount Negative Validation
		if (amount < 0) {
			throw new BankException("!! Amount can't be negative !!");
		}

		// DEBIT ACCOUNT Details
		Optional<Customer> debitCust = customerRepository.findById(debitcustId);
		if (!debitCust.isPresent()) {
			throw new BankException("!! Source Customer Not Found !!");
		}
		debitAcc = accountRepository.getAccountByCustId(debitcustId);
		debitBank = debitCust.get().getBank();
		System.out.println("---------Fetching  Transaction Pass -----");
		debitpasswordDetails = passwordDetailsRepository.getPasswordByCustId(debitcustId);
		System.out.println("--------- After Fetching  Transaction Pass -----");
		// Insufficient Balance Validation
		if (debitAcc.getAvailableBalance() < amount) {
			throw new BankException("!! Insufficient Balance !!");
		}
		System.out.println("--------- Amount Validating -----");

		// Transaction Password Validation
		if (!Objects.isNull(debitpasswordDetails)) {
			if (!debitpasswordDetails.getNewTransactionPassword().equals(transDto.getTransPass())) {
				throw new BankException("!! Please Enter Valid Transaction Password !!");
			}
		}

		// CREDIT ACCOUNT Details
		Optional<Account> creditAcc = accountRepository.findById(destAcc);
		if (!creditAcc.isPresent()) {
			throw new BankException(" !! Destination Customer Not Found !! ");
		}
		crAcc = creditAcc.get();
		creditCust = creditAcc.get().getCustomer();
		creditBank = creditCust.getBank();

		// Destination Customer Validation
		if (!crAcc.getAccountNum().equals(destAcc)) {
			throw new BankException("!! Please Enter Valid Account Number !!");
		}

		if (!crAcc.getIfsc().equals(transDto.getIfsc())) {
			throw new BankException("!! Please Enter Valid IFSC Code !!");
		}

		if (!creditCust.getCustName().equals(transDto.getCustName())) {
			throw new BankException("!! Please Enter Valid Cutomer Name !!");
		}

		// Generation Reference Number
		String referenceNo = getRandomString(15).toUpperCase();
		try {
			// DEBIT
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

			// CREDIT
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
		} catch (Exception e) {
			return new Response(400, "!! Transfer Failed Something went wrong !!");
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
