package com.dbs.payment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="customeruser")
public class CustomerUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userid;

	private String username;
	
	@OneToOne
	@JoinColumn(name="customerid")
	private Customer customer;
	
	private String userpassword;
	
	private String roles;
	
	public CustomerUser() {
		
	}

	public CustomerUser(int userid, String username, Customer customer, String userpassword) {
		super();
		this.userid = userid;
		this.username = username;
		this.customer = customer;
		this.userpassword = userpassword;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
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

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "CustomerUser [userid=" + userid + ", username=" + username + ", customer=" + customer
				+ ", userpassword=" + userpassword + ", roles=" + roles + "]";
	}

	
	
	
	
	
}
