package com.dbs.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.dbs.payment.model.CustomerUser;
import com.dbs.payment.repository.CustomerUserRepository;
import com.dbs.payment.service.CustomerService;

@SpringBootApplication
public class PaymentProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentProjectApplication.class, args);
//		CustomerService cService= applicationContext.getBean(CustomerService.class);
//		CustomerUser user=new CustomerUser(2,"amu", cService.getCustomerByCustomerID("71319440983198"), "abc");
//		user.setRoles("ROLE_USER");
//		CustomerUserRepository repo = applicationContext.getBean(CustomerUserRepository.class);
//		repo.save(user);
	}

}
