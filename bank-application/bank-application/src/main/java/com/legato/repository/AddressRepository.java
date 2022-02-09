package com.legato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.legato.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
