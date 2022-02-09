package com.legato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.legato.entity.TransactionDetails;


@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long> {

}
