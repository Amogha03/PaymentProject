package com.dbs.payment.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.payment.model.Customer;
import com.dbs.payment.model.ResponsePage;
import com.dbs.payment.model.TopCustomer;
import com.dbs.payment.repository.CustomerRepository;
import com.dbs.payment.service.CustomerService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerservice;
	
	@Autowired
	private CustomerRepository customerrepo;
	
	@GetMapping("/{customerid}")
	@ResponseBody
	public ResponseEntity<Object> getCustomers(@PathVariable String customerid) {
		
		try {
		
		Customer customer  = this.customerservice.getCustomerByCustomerID(customerid);
		
		return ResponseEntity.status(HttpStatus.OK).body(customer);
		
		}catch (EntityNotFoundException e) {
			// TODO: handle exception
			
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponsePage("failure",e.getMessage()));
		}
	
	}
	
	@PatchMapping
	public ResponseEntity<List<String>> updateCustomer(@RequestBody Customer customer) {
		
		List<String> list = new ArrayList();
		
		if(this.customerservice.updateCustomer(customer)) {
			list.add("Customer with customer id "+customer.getCustomerid()+" is updated");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
		}
		list.add("product with "+customer.getCustomerid()+"  not found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(list);
		
	}
	
	
	@GetMapping("/topcustomers")
	public ResponseEntity<List<TopCustomer>> getTopCustomerList() {
		
		List<TopCustomer> topcustomers = (List<TopCustomer>) customerrepo.getTopCustomerList();
		
//		Optional<TopCustomer> opt = (Optional<TopCustomer>) customerrepo.getTopCustomerList();
		
		return new ResponseEntity<>(topcustomers,HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
