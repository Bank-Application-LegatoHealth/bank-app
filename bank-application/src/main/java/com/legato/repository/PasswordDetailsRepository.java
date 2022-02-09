package com.legato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.legato.entity.PasswordDetails;


@Repository
public interface PasswordDetailsRepository extends JpaRepository<PasswordDetails, Long> {

}
