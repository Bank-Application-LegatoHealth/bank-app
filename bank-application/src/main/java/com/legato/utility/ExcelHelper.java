package com.legato.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.legato.dto.TransactionResponseDTO;

public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  static String[] HEADERs = {"Transaction Id", "Reference No.","Account No.","Transaction Type","Amount","Date","IFSC" };
	  
	  static String SHEET = "Transaction Details";
	  public static ByteArrayInputStream tutorialsToExcel(List<TransactionResponseDTO> transactions) {
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
	      
	      
	      for (TransactionResponseDTO trans : transactions) {
	        Row row = sheet.createRow(rowIdx++);
	        row.createCell(0).setCellValue(trans.getTransactionId());
	        row.createCell(1).setCellValue(trans.getReferenceNo());
	        row.createCell(2).setCellValue(trans.getAccountNum());
	        row.createCell(3).setCellValue(trans.getTransactionType().toString());
	        row.createCell(4).setCellValue(trans.getAmount());
	        row.createCell(5).setCellValue(trans.getTransactionDateTime());
	        row.createCell(6).setCellValue(trans.getIfsc());
	      }
	      workbook.write(out);
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("Fail to import data to Excel file: " + e.getMessage());
	    }
	  }
}
