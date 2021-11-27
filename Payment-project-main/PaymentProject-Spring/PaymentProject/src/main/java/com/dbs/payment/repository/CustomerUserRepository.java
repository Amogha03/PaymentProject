package com.dbs.payment.repository;

import org.springframework.data.repository.CrudRepository;

import com.dbs.payment.model.CustomerUser;

public interface CustomerUserRepository extends CrudRepository<CustomerUser, Integer>{

}
