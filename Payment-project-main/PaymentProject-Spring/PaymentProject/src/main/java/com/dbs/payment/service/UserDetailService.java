package com.dbs.payment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dbs.payment.model.CustomerUser;
import com.dbs.payment.model.MyUserDetails;
import com.dbs.payment.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userrepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<CustomerUser> opt = userrepository.findByUsername(username);
		
		opt.orElseThrow(()->new UsernameNotFoundException("NOT FOUND "+username));
		
		return opt.map(MyUserDetails::new).get();
	}

}
