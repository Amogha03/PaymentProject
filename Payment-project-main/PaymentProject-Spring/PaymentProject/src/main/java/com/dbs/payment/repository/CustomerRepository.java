package com.dbs.payment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dbs.payment.model.Customer;
import com.dbs.payment.model.TopCustomer;

public interface CustomerRepository extends CrudRepository<Customer, String>{
	
	@Query(value="SELECT customer.accountholdername as name,sum(currencyamount)as amount FROM transaction join customer "
			+ "on transaction.customerid=customer.customerid "
			+ "group by transaction.customerid", nativeQuery = true)
    List<?> getTopCustomerList();
//    Optional<?> getTopCustomerList();


}
