package com.legato.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legato.dto.AccountDetailResponseDTO;
import com.legato.dto.TransactionRequestDTO;
import com.legato.dto.TransactionResponseDTO;
import com.legato.entity.Account;
import com.legato.entity.Customer;
import com.legato.entity.TransactionDetails;
import com.legato.exception.BankException;
import com.legato.repository.AccountRepository;
import com.legato.repository.CustomerRepository;
import com.legato.repository.TransactionDetailsRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private TransactionDetailsRepository repository;
	
	@Autowired
	private AccountRepository accrepository;
	
	@Autowired
	private CustomerRepository custrepository;
	
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = {"Transaction Id", "Reference No.","Account No.","Transaction Type","Amount","Date","IFSC" };
	  
	static String SHEET = "Transaction Details";
	
	public List<TransactionResponseDTO> fetchTransactions(TransactionRequestDTO request) throws BankException {
		List<TransactionDetails> allTransactions = new ArrayList<TransactionDetails>();
		
		//If no filters are applied, display all data when user enters submit
		//By default showAll radio button would be enabled	=> isFilterSet = False
		if(!request.isFilterSet()) {
			allTransactions = repository.findAll();
		}
		
		//filter by date radio button which displays FromDate and ToDate  => isFilterSet = True
		if(request.isFilterSet() && (Objects.nonNull(request.getFromDate()) && Objects.nonNull(request.getToDate()))) {
			allTransactions = repository.getAllBetweenDates(request.getFromDate(), request.getToDate());
		}
		
		//filter by debit or credit transactiontype	=> 	isFilterSet = True
		if(request.isFilterSet() && (Objects.nonNull(request.getTransType()))) {
			
			allTransactions = repository.findByCreditOrDebit(request.getTransType());
		}
		
		
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
	
	
	//Service call to download transactions as Excel sheet
	@Override
	  public ByteArrayInputStream transToExcel(TransactionRequestDTO request) throws BankException {
		
		List<TransactionResponseDTO> transaction;
		try {
			transaction = fetchTransactions(request);
		} catch (BankException e1) {
		      throw new BankException("No transactions found ");
		}
		
		
	    try (Workbook workbook = new XSSFWorkbook(); 
	    		ByteArrayOutputStream out = new ByteArrayOutputStream();) {
	      Sheet sheet = workbook.createSheet(SHEET);
	      // Header
	      Row headerRow = sheet.createRow(0);
	      for (int col = 0; col < HEADERs.length; col++) {
	        Cell cell = headerRow.createCell(col);
	        cell.setCellValue(HEADERs[col]);
	      }
	      int rowIdx = 1;
	      
	      
	      for (TransactionResponseDTO trans : transaction) {
	        Row row = sheet.createRow(rowIdx++);
	        row.createCell(0).setCellValue(Objects.isNull(trans.getTransactionId())? "":trans.getTransactionId().toString());
	        row.createCell(1).setCellValue(Objects.isNull(trans.getReferenceNo())?"":trans.getReferenceNo());
	        row.createCell(2).setCellValue(Objects.isNull(trans.getAccountNum())?"":trans.getAccountNum().toString());
	        row.createCell(3).setCellValue(Objects.isNull(trans.getTransactionType())?"":trans.getTransactionType().toString());
	        row.createCell(4).setCellValue(Objects.isNull(trans.getAmount())?"":trans.getAmount().toString());
	        row.createCell(5).setCellValue(Objects.isNull(trans.getTransactionDateTime())?"":trans.getTransactionDateTime().toString());
	        row.createCell(6).setCellValue(Objects.isNull(trans.getIfsc())?"":trans.getIfsc());
	      }
	      workbook.write(out);
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("Fail to import data to Excel file: " + e.getMessage());
	    }
	  }

	@Override
	public AccountDetailResponseDTO getAccountDetail(Long l) throws BankException {
		
		
		AccountDetailResponseDTO accountDetailResponseDTO = null;
		
		Optional<Customer> customerOptional= custrepository.findById(l);
		Customer c= customerOptional.get();
		
		
		Account account= accrepository.getAccountByCustId(l);
		
		
		return new AccountDetailResponseDTO(c.getCustName(), account.getAccountNum(), account.getAccType().toString(), account.getAvailableBalance());
	}

}
