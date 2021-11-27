package com.dbs.payment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dbs.payment.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer>{

	@Query(value="select * from transaction where customerid=:customerid", nativeQuery = true)
	List<Transaction> findByCustomerid(@Param("customerid")String customerid);
}
