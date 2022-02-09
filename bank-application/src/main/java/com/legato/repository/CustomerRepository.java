package com.legato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.legato.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
