package com.legato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.legato.entity.PasswordDetails;


@Repository
public interface PasswordDetailsRepository extends JpaRepository<PasswordDetails, Long> {
	
	@Query("select p from PasswordDetails p where p.customer.custId = :custId")
	public PasswordDetails getPasswordByCustId(Long custId);

}
