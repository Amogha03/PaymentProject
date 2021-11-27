package com.dbs.payment.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.payment.model.Bank;
import com.dbs.payment.repository.BankRepository;

@Service
public class BankService {
	
	@Autowired
	private BankRepository bankrepo;
	
	public Bank findBankNameById(String BIC) {
		
		try {
		
			System.out.println("debug bank bic "+BIC);
		    Optional<Bank> opt = bankrepo.findById(BIC);
		    
		    
		    return opt.orElseThrow(()->{
		    	return new EntityNotFoundException("Institution Not Found with BIC "+ BIC);
		    });
		 
		
		}catch(IllegalArgumentException e) {
			
			return null;
			
		}

	}

}
