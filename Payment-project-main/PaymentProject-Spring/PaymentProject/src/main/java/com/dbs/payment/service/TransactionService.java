package com.dbs.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.payment.model.Customer;
import com.dbs.payment.model.Transaction;
import com.dbs.payment.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository repo;
	
    public boolean updateTransaction(Transaction transaction) {
		
		try {
		    this.repo.save(transaction);
		}catch(IllegalArgumentException e) {
			return false;
		}
		return true;
	}

}
