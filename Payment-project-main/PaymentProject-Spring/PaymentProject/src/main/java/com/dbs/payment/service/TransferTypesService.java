package com.dbs.payment.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.payment.model.TransferTypes;
import com.dbs.payment.repository.TransferTypesRepository;

@Service
public class TransferTypesService {

    @Autowired
	private TransferTypesRepository repo;
    
    public TransferTypes findTransferTypeByID(String transfertypecode) {
    	try {
    	     Optional<TransferTypes> opt = this.repo.findById(transfertypecode);
    	     
    	     return opt.orElseThrow(()->{
    	    	 return new EntityNotFoundException("Transfer type not found with "+transfertypecode);
    	     });
    	}catch(IllegalArgumentException e) {
    		
    		return null;
    		
    	}
    	
    }
	
	
}
