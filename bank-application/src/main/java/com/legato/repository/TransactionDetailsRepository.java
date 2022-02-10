package com.legato.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.legato.entity.TransactionDetails;
import com.legato.utility.TransactionType;


@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long> {
	
	@Query(value = "SELECT t from TransactionDetails t where date(t.transactionDateTime) BETWEEN :startDate AND :endDate")
	public List<TransactionDetails> getAllBetweenDates(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	
	
	@Query(value = "SELECT t from TransactionDetails t where t.transactionType = :trans")
	public List<TransactionDetails> findByCreditOrDebit(@Param("trans")TransactionType trans);
	
	

}
