package com.dbs.payment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.payment.model.AuthenticationRequest;
import com.dbs.payment.model.AuthenticationResponse;
import com.dbs.payment.service.UserDetailService;
import com.dbs.payment.util.JWTUtil;

@RestController
@CrossOrigin(origins="*")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationmanager;
	
	@Autowired
	private UserDetailService userdetailsservice;
	
	@Autowired
	private JWTUtil jwtutil;
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> DoAuthentication(@RequestBody AuthenticationRequest authenticationrequest)  throws Exception{
		
		try {
		this.authenticationmanager.authenticate(
				
	      new UsernamePasswordAuthenticationToken(authenticationrequest.getUsername(), authenticationrequest.getPassword())
		
		);
		}catch(BadCredentialsException e) {
		   throw new Exception("Incorrect Username or Password ",e);
		}
		
		final UserDetails userdetails  = userdetailsservice.loadUserByUsername(authenticationrequest.getUsername());
		
		final String jwt = jwtutil.generateToken(userdetails);
		
		return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(jwt));
		
		
		
		
	}

}
