package com.dbs.payment.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.payment.model.Customer;
import com.dbs.payment.model.CustomerUser;
import com.dbs.payment.repository.UserRepository;
import com.dbs.payment.util.JWTUtil;

@RestController
@CrossOrigin
@RequestMapping("/customeruser")
public class CustomerUserController {
	
	@Autowired
	private JWTUtil jwtutil;
	
	@Autowired
	private UserRepository userrepository;
	
	@GetMapping("/getuser")
	public ResponseEntity<?> findCustomerid(HttpServletRequest request) throws UsernameNotFoundException {
		
        final String authorizationHeader = request.getHeader("Authorization");
		
		String username = null;
		
		String jwt = null;
		
		if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			
			username= jwtutil.extractUsername(jwt);
		}
		Optional<CustomerUser> opt = userrepository.findByUsername(username);
		
		
		opt.orElseThrow(()->new UsernameNotFoundException("NOT FOUND "));
				
		return ResponseEntity.status(HttpStatus.OK).body(new SampleCustomer(opt.get().getUsername(), opt.get().getCustomer(), opt.get().getRoles()));
		
		
	}

}


class SampleCustomer{
	private String username;
	private Customer customer;
	private String roles;
	public SampleCustomer(String username, Customer customer, String roles) {
		super();
		this.username = username;
		this.customer = customer;
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
	
}