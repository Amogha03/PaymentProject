package com.dbs.payment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbs.payment.model.CustomerUser;

public interface UserRepository extends JpaRepository<CustomerUser, Integer>{

	Optional<CustomerUser> findByUsername(String username);
	
}
