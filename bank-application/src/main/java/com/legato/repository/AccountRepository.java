package com.legato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.legato.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	@Query("select u from Account u where u.customer.custId = :custId")
	public Account getAccountByCustId(Long custId);
}
