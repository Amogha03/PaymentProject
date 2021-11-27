package com.dbs.payment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.payment.model.Bank;
import com.dbs.payment.service.BankService;

@RestController
@CrossOrigin
//@CrossOrigin(origins="localhost:4200")
@RequestMapping("/bank")
public class BankController {
	
	@Autowired
	private BankService bankservice;
	

	@GetMapping("/{BIC}")
	public ResponseEntity<String> findBankName(@PathVariable String BIC) {
		
	     Bank bank = bankservice.findBankNameById(BIC);
	     
	     if(bank!=null) {
	    	 	    	 
	    	 return ResponseEntity.status(HttpStatus.OK).body(bank.getBankname());
	    	 
	     }else {
	    	 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Institution found with BIC "+BIC);
	     }
		
	}

}
