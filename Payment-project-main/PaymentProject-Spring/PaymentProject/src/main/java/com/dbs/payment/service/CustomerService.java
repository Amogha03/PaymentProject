package com.dbs.payment.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.dbs.payment.model.Customer;
import com.dbs.payment.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repo;
	
	public Customer getCustomerByCustomerID(String customerid) {
		
		try {
			Optional<Customer> cust  = this.repo.findById(customerid);
			
			return cust.orElseThrow(()->{
				return new EntityNotFoundException("customer with "+customerid+" not found");
			});
			
		}catch(IllegalArgumentException e) {
			
			return null;
			
		}
		
		
	}
	
	public boolean updateCustomer(Customer customer) {
		
		try {
		    this.repo.save(customer);
		}catch(IllegalArgumentException e) {
			return false;
		}
		return true;
	}
	
	
	
	
	
	

}
