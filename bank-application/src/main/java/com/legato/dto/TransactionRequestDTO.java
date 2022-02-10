package com.legato.dto;

import java.util.Date;

import com.legato.utility.TransactionType;

public class TransactionRequestDTO {
	
	private Date fromDate;
	
	private Date toDate;
	
	private TransactionType transType;
	
	private boolean filterSet;

	public TransactionRequestDTO(Date fromDate, Date toDate, TransactionType transType, boolean filterSet) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.transType = transType;
		this.filterSet = filterSet;
	}

	public boolean isFilterSet() {
		return filterSet;
	}

	public void setFilterSet(boolean filterSet) {
		this.filterSet = filterSet;
	}

	public TransactionType getTransType() {
		return transType;
	}

	public void setTransType(TransactionType transType) {
		this.transType = transType;
	}

	public TransactionRequestDTO() {
		super();
	}

	
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	

}
